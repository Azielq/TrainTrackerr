package com.mycompany.traintrack.client.igu;

public abstract class Train implements Runnable {
    
    protected int[] position;
    protected int stage;
    protected boolean active;
    protected boolean inStation;

    public static final int stationTime = 10000;

    pnlTrainMap trainPanel;

    public Train(){

    }

}
