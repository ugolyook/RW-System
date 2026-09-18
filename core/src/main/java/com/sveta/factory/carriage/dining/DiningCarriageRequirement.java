package com.sveta.factory.carriage.dining;

import com.sveta.train.carriage.passenger.models.Food;
import com.sveta.factory.carriage.CarriageRequirement;

public record DiningCarriageRequirement(
        int seatsLimit,
        int baseCarriageWeightKg,
        Food food,
        boolean hasHotKitchen,
        boolean deliveryToTheRoom
) implements CarriageRequirement {
}