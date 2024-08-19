package com.mycompany.traintrack.server.logica;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

import javax.swing.SwingUtilities;

public class ServerTrainRunnable implements Runnable {
    private final Train train;
    private final List<Station> route;
    private final Station syncStation;
    private final Train syncTrain;
    private final DatagramSocket socket;
    private final InetAddress clientAddress;
    private final int clientPort;

    // Configuración de tiempos de espera
    private final int regularWaitTime = 500; 
    private final int extendedWaitTime = 2000;

    private boolean firstArrivalAtSyncStation = true;  
    private boolean train4FirstPass = true;
    private volatile boolean running = true;
    private volatile boolean paused = false; 
    private final Station initialStation; 
    private volatile boolean resetRequested = false;

    private static final Object resetLock = new Object();

    public ServerTrainRunnable(Train train, List<Station> route, Station syncStation, Train syncTrain, DatagramSocket socket, InetAddress clientAddress, int clientPort) {
        this.train = train;
        this.route = route;
        this.syncStation = syncStation;
        this.syncTrain = syncTrain;
        this.socket = socket;
        this.clientAddress = clientAddress;
        this.clientPort = clientPort;
        this.initialStation = route.get(0);
    }

    @Override
    public void run() {
        System.out.println(train.getName() + " ha iniciado.");

        if (train.getName().equals("Train4")) {
            synchronized (Station.Heredia) {
                try {
                    System.out.println("Train4 esperando que Train3 llegue a HEREDIA antes de comenzar.");
                    Station.Heredia.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        while (running) {
            synchronized (this) {
                if (resetRequested || shouldStop()) {
                    break;
                }

                while (paused) {
                    try {
                        wait(); // Detener la ejecución del tren mientras esté pausado
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

            for (Station station : route) {
                synchronized (this) {
                    if (resetRequested || shouldStop()) {
                        break;
                    }

                    while (paused) {
                        try {
                            wait(); // Asegurarse de que el tren esté pausado correctamente
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }

                moveTrainToStation(station);

                if (paused || resetRequested) {
                    break; // Salir del bucle si se pausa o se resetea
                }
            }

            // Invertir la ruta para regresar al inicio
            for (int i = route.size() - 2; i >= 0; i--) {
                synchronized (this) {
                    if (resetRequested || shouldStop()) {
                        break;
                    }

                    while (paused) {
                        try {
                            wait(); // Asegurarse de que el tren esté pausado correctamente
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }

                Station station = route.get(i);
                moveTrainToStation(station);

                if (paused || resetRequested) {
                    break; // Salir del bucle si se pausa o se resetea
                }
            }
        }

        if (resetRequested) {
            synchronized (resetLock) {
                resetTrainPosition();
            }
        }

        System.out.println(train.getName() + " ha terminado su recorrido.");
    }

    private void moveTrainToStation(Station station) {
        int startX = train.getPositionX();
        int startY = train.getPositionY();
        int endX = station.getX();
        int endY = station.getY();
    
        // Adjust steps and sleep time for faster movement
        int steps = 100; // Reduce steps for faster movement
        int sleepTime = 5; // Reduce sleep time for faster movement
    
        for (int i = 1; i <= steps; i++) {
            int newX = startX + (endX - startX) * i / steps;
            int newY = startY + (endY - startY) * i / steps;
    
            sendPositionUpdate(train.getTrainIndex(), newX, newY);
    
            try {
                Thread.sleep(sleepTime); // Faster movement with shorter sleep time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    
        // Update train's position to the exact coordinates of the station
        train.setPosition(endX, endY);
        sendPositionUpdate(train.getTrainIndex(), endX, endY);
    
        System.out.println(train.getName() + " ha llegado a " + station.name());
        // After moving the train to the station
        sendCurrentStationUpdate(station);
    
        if (station == syncStation) {
            synchronized (syncStation) {
                if (firstArrivalAtSyncStation && train.getName().equals("Train3")) {
                    System.out.println("Train3 ha llegado a HEREDIA por primera vez. Notificando a Train4 para que comience.");
                    syncStation.notifyAll();
                    firstArrivalAtSyncStation = false;  
                } else {
                    System.out.println(train.getName() + " ha llegado a " + syncStation.name() + ", esperando a " + syncTrain.getName());

                    // After moving the train to the station
                    sendCurrentStationUpdate(station);
    
                    while (!syncTrain.isAtStation(syncStation)) {
                        try {
                            syncStation.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
    
                    syncStation.notifyAll(); 
                }
            }
        }
    
        try {
            Thread.sleep(getWaitTimeForStation(station)); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void updateGUIPosition() {
        SwingUtilities.invokeLater(() -> {
            sendPositionUpdate(train.getTrainIndex(), train.getPositionX(), train.getPositionY());
        });
    }

    public synchronized void reset() {
    paused = true;
    running = false;
    resetRequested = true;
    
    notifyAll();  // Notificar a todos para asegurarse de que los trenes se detengan

    stopAllMovements(); // Detenemos completamente todos los movimientos
    
    synchronized (resetLock) {
        resetTrainPosition(); // Reseteamos la posición del tren
    }
    
    resetRequested = false;
    running = false;
}
    private void sendCurrentStationUpdate(Station station) {
        String message = "STATION_UPDATE " + train.getTrainIndex() + " " + station.name();
        byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    private void stopAllMovements() {
        train.stopMovement(); 
    }

    public synchronized void stopAndReset() {
        stop();  // Detenemos el tren
        resetRequested = true;  // Indicamos que se solicita un reset
        notifyAll();  // Notificamos a cualquier hilo esperando
    }
    

    public synchronized void resetTrainPosition() {
        synchronized (resetLock) {
            train.setPosition(initialStation.getX(), initialStation.getY());
            updateGUIPosition(); // Envía la actualización de posición al cliente
            System.out.println(train.getName() + " se ha reseteado a " + initialStation.name());
        }
    }

    private int getWaitTimeForStation(Station station) {
        if (train.getName().equals("Train4") && train4FirstPass && (station == Station.Estación_Atlántico || station == Station.Alajuela)) {
            train4FirstPass = false; 
            return regularWaitTime;
        }

        if (train.getName().equals("Train1") || train.getName().equals("Train2")) {
            if (station == Station.Belén || station == Station.Paraíso) {
                return extendedWaitTime; 
            }
        } else if (train.getName().equals("Train3") || train.getName().equals("Train4")) {
            if (station == Station.Estación_Atlántico || station == Station.Alajuela) {
                return extendedWaitTime;
            }
        }
        return regularWaitTime; 
    }

    public synchronized void pause() {
        paused = true;
    }

    public synchronized void resume() {
        paused = false;
        running = true;
        notifyAll(); // Notificar para reanudar la ejecución
        
        // Actualizar la GUI
        updateGUIPosition();
    }

    public synchronized void stop() {
        running = false;
        notifyAll(); 
    }

    private boolean shouldStop() {
        String currentTime = ClockManager.getCurrentTime();
        if (train.getName().equals("Train3") || train.getName().equals("Train4")) {
            return currentTime.compareTo("18:00") >= 0;
        }
        return currentTime.compareTo("20:00") >= 0;
    }

    private void sendPositionUpdate(int trainIndex, int x, int y) {
        String message = "POSITION_UPDATE " + trainIndex + " " + x + " " + y;
        byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}