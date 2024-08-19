
package com.mycompany.traintrack.client.igu;

import java.util.List;

public class TrainRunnable implements Runnable {
    private final Train train;
    private final List<Station> route;
    private final Station syncStation;
    private final Train syncTrain;
    private final int regularWaitTime = 500; 
    private final int extendedWaitTime = 2000;
    
    private boolean firstArrivalAtSyncStation = true;  
    private boolean train4FirstPass = true;
    private volatile boolean running = true;
    private volatile boolean paused = false; 
    private final Station initialStation; 
    private volatile boolean resetRequested = false; // Bandera para manejar el reset

    public TrainRunnable(Train train, List<Station> route, Station syncStation, Train syncTrain) {
        this.train = train;
        this.route = route;
        this.syncStation = syncStation;
        this.syncTrain = syncTrain;
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

    while (true) {
        // Verifica si debe detenerse el hilo
        synchronized (this) {
            while (paused) {
                try {
                    wait(); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        if (resetRequested || !running) {
            break; 
        }

        for (Station station : route) {
            // Verifica si debe detenerse el hilo antes de moverse
            synchronized (this) {
                while (paused) {
                    try {
                        wait(); 
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

            if (shouldStop() || resetRequested) break;

            boolean reached = train.moveToStation(station);

            if (reached) {
                System.out.println(train.getName() + " ha llegado a " + station.name());

                if (station == syncStation) {
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

                try {
                    Thread.sleep(getWaitTimeForStation(station)); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        for (int i = route.size() - 2; i >= 0; i--) {
            // Verifica si debe detenerse el hilo antes de moverse en reversa
            synchronized (this) {
                while (paused) {
                    try {
                        wait(); 
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

            if (shouldStop() || resetRequested) break;

            Station station = route.get(i);
            boolean reached = train.moveToStation(station);

            if (reached) {
                System.out.println(train.getName() + " ha llegado a " + station.name());

                if (station == syncStation) {
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

                try {
                    Thread.sleep(getWaitTimeForStation(station)); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    if (resetRequested) {
        resetTrainPosition();
    }

    System.out.println(train.getName() + " ha terminado su recorrido.");
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
        notifyAll(); 
    }

    public synchronized void stop() {
        running = false;
        notifyAll(); 
    }

    public synchronized void reset() {
        resetRequested = true; 
        running = false; 
        resume(); 
    }

    private void resetTrainPosition() {
        train.moveToStation(initialStation); 
        resetRequested = false; 
        System.out.println(train.getName() + " se ha reseteado a " + initialStation.name());
    }

    private boolean shouldStop() {
        String currentTime = ClockManager.getCurrentTime();
        if (train.getName().equals("Train3") || train.getName().equals("Train4")) {
            return currentTime.compareTo("18:00") >= 0;
        }
        return currentTime.compareTo("20:00") >= 0;
    }
}