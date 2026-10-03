package com.sveta.train.carriage;

public abstract class Carriage {
    private final int baseCarriageWeightInKg;

    public Carriage(int baseCarriageWeightInKg) {
        this.baseCarriageWeightInKg = baseCarriageWeightInKg;
    }

    public int getKgWeight() {
        return baseCarriageWeightInKg;
    }

    public abstract int getPassengerCapacity();
}