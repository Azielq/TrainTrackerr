package com.mycompany.traintrack.client.igu;

public abstract class Train{
    
    protected int[] position;
    protected boolean active;
    protected pnlTrainMap trainPanel;
    private final String name;
    private final int trainIndex;
    private volatile boolean stopMovement; // Añadido para controlar el movimiento

    public static final int stationTime = 10000;

    public Train(pnlTrainMap panel, Station startStation, String name, int trainIndex) {
        this.trainPanel = panel;
        this.position = new int[]{startStation.getX(), startStation.getY()};
        this.active = false;
        this.name = name;
        this.trainIndex = trainIndex;
        this.stopMovement = false; // Inicialmente, el movimiento no se detiene
    }

    protected boolean moveToStation(Station destination) {
        while (position[0] != destination.getX() || position[1] != destination.getY()) {
            if (stopMovement) {
                System.out.println(name + " ha detenido su movimiento.");
                return false; // Salir del bucle si se solicita detener el movimiento
            }

            if (position[0] < destination.getX()) position[0]++;
            if (position[0] > destination.getX()) position[0]--;
            if (position[1] < destination.getY()) position[1]++;
            if (position[1] > destination.getY()) position[1]--;

            trainPanel.updateTrainPosition(trainIndex, position[0], position[1]);

            try {
                Thread.sleep(10); // Simulación del movimiento
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false; // Salir del bucle si el hilo es interrumpido
            }
        }
        return true;
    }

    public boolean isAtStation(Station station) {
        return position[0] == station.getX() && position[1] == station.getY();
    }

    public void setPosition(int x, int y) {
        this.position[0] = x;
        this.position[1] = y;
        trainPanel.updateTrainPosition(trainIndex, x, y); // Actualizar la posición en el panel
    }

    public void resetPosition(Station station) {
        setPosition(station.getX(), station.getY());
        System.out.println(getName() + " ha sido reposicionado a " + station.name());
    }

    public void stopMovement() {
        stopMovement = true; // Indica que el movimiento debe detenerse
        System.out.println(getName() + " ha solicitado detener su movimiento.");
    }

    public void resumeMovement() {
        stopMovement = false; // Permite que el tren vuelva a moverse
    }

    public String getName() {
        return name;
    }

    public int getTrainIndex() {
        return trainIndex;
    }
}