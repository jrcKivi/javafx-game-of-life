package com.imaginarium.gameoflife.utils;

import javafx.animation.AnimationTimer;

public class CycleOfLife extends AnimationTimer {
    
    private long lastUpdate = 0;
    private int speed = 10;
    private Context context;

    private final int SPEED_MIN = 1;
    private final int SPEED_MAX = 60;

    private volatile boolean running;

    public CycleOfLife(Context ctx) {
        this.context = ctx;
    }

    @Override
    public void handle(long now) {
        if (now - lastUpdate >= 1_000_000_000 / speed) {
            context.getLife().live(context.getWorld());
            lastUpdate = now ;
        }
    }

    @Override
    public void start() {
         super.start();
         running = true;
    }

    @Override
    public void stop() {
        super.stop();
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSPEED_MIN() {
        return this.SPEED_MIN;
    }

    public int getSPEED_MAX() {
        return this.SPEED_MAX;
    }
}