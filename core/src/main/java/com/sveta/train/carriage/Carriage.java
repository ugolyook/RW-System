package com.sveta.train.carriage;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class Carriage {
    private final int baseCarriageWeightInKg;

    protected Carriage(int baseCarriageWeightInKg) {
        this.baseCarriageWeightInKg = baseCarriageWeightInKg;
    }

    public int getKgWeight() {
        return baseCarriageWeightInKg;
    }

    public abstract int getPassengerCapacity();
}