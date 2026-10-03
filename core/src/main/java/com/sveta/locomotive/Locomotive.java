package com.sveta.locomotive;

public class Locomotive {
    private final int maxTransportedWeight;
    private final int wagonLimit;
    private final int maxSpeed;
    private final int power;
    private final int tractionForce;
    private final boolean electric;

    public Locomotive(
            int wagonLimit,
            int maxSpeed,
            int maxTransportedWeight,
            int power,
            int tractionForce,
            boolean isElectric
    ) {
        this.wagonLimit = wagonLimit;
        this.maxSpeed = maxSpeed;
        this.maxTransportedWeight = maxTransportedWeight;
        this.power = power;
        this.tractionForce = tractionForce;
        this.electric = isElectric;
    }
}