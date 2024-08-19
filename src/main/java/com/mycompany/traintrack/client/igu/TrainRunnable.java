
package com.mycompany.traintrack.client.igu;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class TrainRunnable implements Runnable {
    private final Train train;
    private final List<Station> route;
    private final Station syncStation;
    private final Train syncTrain;
    private Calendar calendar;
    private final SimpleDateFormat timeFormat;
    
    private final String stopTime; // Formato "HH:mm"

    public TrainRunnable(Train train, List<Station> route, Station syncStation, Train syncTrain, String stopTime) {
        this.train = train;
        this.route = route;
        this.syncStation = syncStation;
        this.syncTrain = syncTrain;
        this.stopTime = stopTime;
        this.calendar = Calendar.getInstance();
        this.calendar.set(Calendar.HOUR_OF_DAY, 5);
        this.calendar.set(Calendar.MINUTE, 0);
        this.calendar.set(Calendar.SECOND, 0);
        this.timeFormat = new SimpleDateFormat("HH:mm");
    }

    @Override
    public void run() {
        System.out.println(train.getName() + " ha iniciado.");

        // Si es Tren 4, espera a que el Tren 3 llegue a HEREDIA antes de comenzar
        if (train.getName().equals("Train4")) {
            synchronized (Station.Heredia) {
                try {
                    System.out.println("Train4 esperando que Train3 llegue a HEREDIA antes de comenzar.");
                    Station.Heredia.wait();  // Tren 4 espera a que Tren 3 llegue a HEREDIA
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        while (!shouldStop()) {
            // Mover hacia adelante en la ruta
            for (Station station : route) {
                if (shouldStop()) break;

                boolean reached = train.moveToStation(station);

                if (reached) {
                    System.out.println(train.getName() + " ha llegado a " + station.name());

                    // Sincronización en HEREDIA
                    if (station == Station.Heredia && (train.getName().equals("Train3") || train.getName().equals("Train4"))) {
                        synchronized (Station.Heredia) {
                            System.out.println(train.getName() + " ha llegado a HEREDIA, esperando a " + syncTrain.getName());
                            try {
                                Station.Heredia.notifyAll(); // Notifica a otros trenes
                                Station.Heredia.wait();  // Espera a que el otro tren llegue
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                // Simular la espera en la estación (tiempo en la estación)
                try {
                    Thread.sleep(500); // Simulación de tiempo de espera
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Mover de regreso en la ruta (inverso)
            for (int i = route.size() - 2; i >= 0; i--) {
                if (shouldStop()) break;

                Station station = route.get(i);
                boolean reached = train.moveToStation(station);

                if (reached) {
                    System.out.println(train.getName() + " ha regresado a " + station.name());

                    // Sincronización en HEREDIA en el camino de regreso también
                    if (station == Station.Heredia && (train.getName().equals("Train3") || train.getName().equals("Train4"))) {
                        synchronized (Station.Heredia) {
                            System.out.println(train.getName() + " ha regresado a HEREDIA, esperando a " + syncTrain.getName());
                            try {
                                Station.Heredia.notifyAll(); // Notifica a otros trenes
                                Station.Heredia.wait();  // Espera a que el otro tren llegue
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                try {
                    Thread.sleep(500); // Simulación de tiempo de espera
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(train.getName() + " ha terminado su recorrido.");
    }

    private boolean shouldStop() {
        String currentTime = timeFormat.format(calendar.getTime());
        // Detiene trenes 3 y 4 a las 6:00 PM
        if (train.getName().equals("Train3") || train.getName().equals("Train4")) {
            return currentTime.compareTo("18:00") >= 0;
        }
        // Detiene trenes 1 y 2 a las 8:00 PM
        return currentTime.compareTo("20:00") >= 0;
    }
}