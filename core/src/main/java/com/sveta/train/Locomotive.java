package com.sveta.train;

public class Locomotive {
    int maxTransportedWeight;
    int wagonLimit;
    int maxSpeed;

    public Locomotive(int wagonLimit, int maxSpeed, int maxTransportedWeight){
        this.wagonLimit = wagonLimit;
        this.maxSpeed = maxSpeed;
        this.maxTransportedWeight = maxTransportedWeight;
    }
}
