package com.mycompany.traintrack.client.igu;

public abstract class Train{
    
    protected int[] position;
    protected boolean active;
    protected pnlTrainMap trainPanel;
    private final String name;
    private final int trainIndex;

    public static final int stationTime = 10000;

    public Train(pnlTrainMap panel, Station startStation, String name, int trainIndex) {
        this.trainPanel = panel;
        this.position = new int[]{startStation.getX(), startStation.getY()};
        this.active = false;
        this.name = name;
        this.trainIndex = trainIndex;
    }

    protected boolean moveToStation(Station destination) {
        System.out.println(name + " intentando moverse a " + destination.name() + " desde (" + position[0] + ", " + position[1] + ")");
    
        while (position[0] != destination.getX() || position[1] != destination.getY()) {
            if (position[0] < destination.getX()) position[0]++;
            if (position[0] > destination.getX()) position[0]--;
            if (position[1] < destination.getY()) position[1]++;
            if (position[1] > destination.getY()) position[1]--;
    
            System.out.println(name + " moviéndose a (" + position[0] + ", " + position[1] + ") hacia " + destination.name());
    
            trainPanel.updateTrainPosition(trainIndex, position[0], position[1]);
    
            try {
                Thread.sleep(10); // Simulación del movimiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " ha llegado a " + destination.name());
        return true;
    }

    public boolean isAtStation(Station station) {
        return position[0] == station.getX() && position[1] == station.getY();
    }

    public String getName() {
        return name;
    }
}