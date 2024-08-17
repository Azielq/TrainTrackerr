package com.mycompany.traintrack.client.igu;

public enum Station {
    Belén(42, 298),
    
    Paraíso(855, 235),
    EstaciónAtlántico(480, 237);
    

    private final int x;
    private final int y;

    Station(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
