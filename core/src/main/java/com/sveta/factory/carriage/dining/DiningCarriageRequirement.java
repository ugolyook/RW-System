package com.sveta.factory.carriage.dining;

import com.sveta.carriage.passenger.models.Food;
import com.sveta.carriage.passenger.models.Seat;
import com.sveta.factory.carriage.CarriageRequirement;

import java.util.List;

public record DiningCarriageRequirement(
        int seatsLimit,
        int kitchenWeightKg,
        int seatWeightKg,
        int baseCarriageWeightKg,
        List<Seat> seats,
        Food food,
        boolean hasHotKitchen,
        boolean deliveryToTheRoom
) implements CarriageRequirement {
}
