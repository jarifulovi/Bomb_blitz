package com.example.mines_sweeper.gridLogic;

public class Timer {
    private long startTime;
    private double elapsedTime;

    public Timer() {
        startTime = 0;
        elapsedTime = 0.0;
    }

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        long endTime = System.nanoTime();
        elapsedTime += (endTime - startTime) / 1_000_000_000.0; // Convert nanoseconds to seconds
    }

    public void reset() {
        startTime = 0;
        elapsedTime = 0.0;
    }

    public double getElapsedTime() {
        return elapsedTime;
    }

    public boolean isMinTime(double time){
        return elapsedTime < time;
    }

    public String formatTime() {
        return String.format("%.2f", elapsedTime);
    }
}

