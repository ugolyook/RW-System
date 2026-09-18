package com.sveta.locomotive;

public class Locomotive {
    int maxTransportedWeight;
    int wagonLimit;
    int maxSpeed;

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
    }

    public int getMaxTransportedWeight() {
        return maxTransportedWeight;
    }
}
