package com.sveta.train;

public class Locomotive {
    int maxTransportedWeight;
    int wagonLimit;
    int maxSpeed;
    private final int power;
    private final int tractionForce;
    private final boolean isElectric;

    public Locomotive(int wagonLimit, int maxSpeed, int maxTransportedWeight, int power, int tractionForce, boolean isElectric){
        this.wagonLimit = wagonLimit;
        this.maxSpeed = maxSpeed;
        this.maxTransportedWeight = maxTransportedWeight;
        this.power = power;
        this.tractionForce = tractionForce;
        this.isElectric = isElectric;
    }

    public int getWagonLimit() { return wagonLimit; }
    public int getMaxSpeed() { return maxSpeed; }
    public int getMaxTransportedWeight() { return maxTransportedWeight; }
    public int getPower() { return power; }
    public int getTractionForce() { return tractionForce; }
    public boolean isElectric() { return isElectric; }
}
