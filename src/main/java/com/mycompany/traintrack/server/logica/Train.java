
package com.mycompany.traintrack.server.logica;

import com.mycompany.traintrack.client.igu.pnlTrainMap;


public abstract class Train{
    
    protected int[] position;
    protected boolean active;
    protected pnlTrainMap trainPanel;
    private final String name;
    private final int trainIndex;
    private volatile boolean stopMovement;

    public static final int stationTime = 10000;

    public Train(Station startStation, String name, int trainIndex) {
        this.position = new int[]{startStation.getX(), startStation.getY()};
        this.active = false;
        this.name = name;
        this.trainIndex = trainIndex;
        this.stopMovement = false;
    }

    public boolean isAtStation(Station station) {
        return position[0] == station.getX() && position[1] == station.getY();
    }

    protected boolean moveToStation(Station destination) {
        int startX = position[0];
        int startY = position[1];
        int endX = destination.getX();
        int endY = destination.getY();
        int steps = 100; // Increase the number of steps to smooth out the movement
        for (int i = 0; i <= steps; i++) {
            if (stopMovement) {
                System.out.println(name + " ha detenido su movimiento.");
                return false;
            }
            
            double t = (double) i / steps;
            int currentX = (int) ((1 - t) * startX + t * endX);
            int currentY = (int) ((1 - t) * startY + t * endY);
            
            setPosition(currentX, currentY);
    
            try {
                Thread.sleep(5); // Shorter sleep time for smoother movement
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return true;
    }

    public void setPosition(int x, int y) {
        this.position[0] = x;
        this.position[1] = y;
        if (trainPanel != null) {
            trainPanel.updateTrainPosition(trainIndex, x, y);
        }
    }

    public void resetPosition(Station station) {
        setPosition(station.getX(), station.getY());
        System.out.println(getName() + " ha sido reposicionado a " + station.name());
    }

    public void stopMovement() {
        stopMovement = true; 
        System.out.println(getName() + " ha solicitado detener su movimiento.");
    }

    public void resumeMovement() {
        stopMovement = false; 
    }

    public String getName() {
        return name;
    }

    public int getTrainIndex() {
        return trainIndex;
    }

    public int getPositionX() {
        return position[0];
    }

    public int getPositionY() {
        return position[1];
    }
}