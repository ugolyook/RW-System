package com.sveta.factory.carriage.economy;

import com.sveta.factory.carriage.CarriageRequirement;

public record EconomyCarriageRequirement(
        int seatsLimit,
        int baseCarriageWeightKg,
        boolean hasBioToilets
) implements CarriageRequirement {
    @Override
    public int getWeightInKg() {
        return baseCarriageWeightKg;
    }
}