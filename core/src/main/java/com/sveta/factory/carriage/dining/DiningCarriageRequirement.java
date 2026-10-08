package com.sveta.factory.carriage.dining;

import com.sveta.train.carriage.passenger.models.Food;
import com.sveta.factory.carriage.CarriageRequirement;

import java.util.List;

public record DiningCarriageRequirement(
        int seatsLimit,
        int baseCarriageWeightKg,
        List<Food> food,
        boolean hasHotKitchen,
        boolean deliveryToTheRoom
) implements CarriageRequirement {
    @Override
    public int getWeightInKg() {
        return baseCarriageWeightKg;
    }
}