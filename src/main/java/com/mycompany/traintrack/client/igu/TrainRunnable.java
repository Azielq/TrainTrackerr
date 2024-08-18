
package com.mycompany.traintrack.client.igu;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class TrainRunnable implements Runnable {
    private final Train train;
    private final List<Station> route;
    private final Station syncStation;
    private final Train syncTrain;
    private final String stopTime;

public TrainRunnable(Train train, List<Station> route, Station syncStation, Train syncTrain, String stopTime) {
        this.train = train;
        this.route = route;
        this.syncStation = syncStation;
        this.syncTrain = syncTrain;
        this.stopTime = stopTime;
    }

    @Override
    public void run() {
        System.out.println(train.getName() + " ha iniciado.");

        while (true) { // Loop infinitely
            // Mover hacia adelante en la ruta
            for (Station station : route) {
                boolean reached = train.moveToStation(station);

                if (reached) {
                    System.out.println(train.getName() + " ha llegado a " + station.name());
                }

                // Simular la espera en la estación
                try {
                    Thread.sleep(500); // Simulación de tiempo de espera
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Mover de regreso en la ruta (inverso)
            for (int i = route.size() - 2; i >= 0; i--) {
                Station station = route.get(i);
                boolean reached = train.moveToStation(station);

                if (reached) {
                    System.out.println(train.getName() + " ha regresado a " + station.name());
                }

                try {
                    Thread.sleep(500); // Simulación de tiempo de espera
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}