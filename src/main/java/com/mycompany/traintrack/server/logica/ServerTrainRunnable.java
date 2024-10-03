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

    // Sincronización inicial si es Train4
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
                stop(); // Detener completamente el tren
                break;
            }

            while (paused || !running) {
                try {
                    wait(); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        for (Station station : route) {
            synchronized (this) {
                if (resetRequested || shouldStop()) {
                    stop(); // Detener completamente el tren
                    break;
                }

                while (paused || !running) {
                    try {
                        wait(); 
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

            boolean reached = moveTrainToStation(station);

            if (reached && station == syncStation) {
                synchronized (syncStation) {
                    if (firstArrivalAtSyncStation && train.getName().equals("Train3")) {
                        System.out.println("Train3 ha llegado a HEREDIA por primera vez. Notificando a Train4 para que comience.");
                        syncStation.notifyAll();
                        firstArrivalAtSyncStation = false;  
                    } else {
                        System.out.println(train.getName() + " ha llegado a " + syncStation.name() + ", esperando a " + syncTrain.getName());

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
        }

        // Reversa, similar al ciclo hacia adelante
        for (int i = route.size() - 2; i >= 0; i--) {
            synchronized (this) {
                if (resetRequested || shouldStop()) {
                    stop(); // Detener completamente el tren
                    break;
                }

                while (paused || !running) {
                    try {
                        wait(); 
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

            Station station = route.get(i);
            boolean reached = moveTrainToStation(station);

            if (reached && station == syncStation) {
                synchronized (syncStation) {
                    System.out.println(train.getName() + " ha llegado a " + syncStation.name() + ", esperando a " + syncTrain.getName());

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
    }

    if (resetRequested) {
        synchronized (resetLock) {
            resetTrainPosition();
        }
    }

    System.out.println(train.getName() + " ha terminado su recorrido.");
}

private boolean moveTrainToStation(Station station) {
    int startX = train.getPositionX();
    int startY = train.getPositionY();
    int endX = station.getX();
    int endY = station.getY();
    
    int steps = 100; // Número de pasos para suavizar el movimiento
    int sleepTime = 5; // Tiempo de espera para suavizar el movimiento

    for (int i = 1; i <= steps; i++) {
        synchronized (this) {
            // Verifica si se debe detener el tren
            if (resetRequested || shouldStop()) {
                stop(); // Detener completamente el tren
                return false;
            }

            // Manejo de la pausa
            while (paused || !running) {
                try {
                    wait(); // Pausar el movimiento si es necesario
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return false;
                }
            }
        }

        int newX = startX + (endX - startX) * i / steps;
        int newY = startY + (endY - startY) * i / steps;

        // Actualiza la posición del tren
        train.setPosition(newX, newY);
        sendPositionUpdate(train.getTrainIndex(), newX, newY);

        try {
            Thread.sleep(sleepTime); // Movimiento suave
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    // Asegurarse de que el tren esté en la posición exacta de la estación
    train.setPosition(endX, endY);
    sendPositionUpdate(train.getTrainIndex(), endX, endY);

    System.out.println(train.getName() + " ha llegado a " + station.name());

    // Aplicar el tiempo de espera en la estación antes de continuar
    try {
        Thread.sleep(getWaitTimeForStation(station)); 
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return false;
    }

    // Después de mover el tren a la estación, notificar a la GUI y otras operaciones
    sendCurrentStationUpdate(station);

    return true; // Indicar que el tren ha llegado con éxito a la estación
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
    
        // Notificar a cualquier hilo que esté esperando para que se detenga
        notifyAll();  
    
        // Realizar el reset en el EDT para evitar bloquear la UI
        SwingUtilities.invokeLater(() -> {
            stopAllMovementsAndReset();
            resetRequested = false;
            stop();
        });
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

    private void stopAllMovementsAndReset() {
        synchronized (resetLock) {
            stopAllMovements(); // Detener todos los movimientos
            resetTrainPosition(); // Resetear la posición del tren
        }
    }

    private void stopAllMovements() {
        train.stopMovement(); // Detener todos los movimientos del tren
    }

    private void resetTrainPosition() {
        train.setPosition(initialStation.getX(), initialStation.getY()); // Reposicionar el tren
        System.out.println(train.getName() + " se ha reseteado a " + initialStation.name());
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
        System.out.println(train.getName() + " ha sido detenido.");
    }

    private boolean shouldStop() {
        String currentTime = ClockManager.getCurrentTime();
        if ((train.getName().equals("Train3") || train.getName().equals("Train4")) && currentTime.compareTo("18:00") >= 0) {
            System.out.println(train.getName() + " debe detenerse porque son las " + currentTime);
            return true;
        }
        if (currentTime.compareTo("20:00") >= 0) {
            System.out.println(train.getName() + " debe detenerse porque son las " + currentTime);
            return true;
        }
        return false;
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