package com.sveta.factory.carriage.economy;

import com.sveta.carriage.passenger.models.Seat;
import com.sveta.factory.carriage.CarriageRequirement;

import java.util.List;

public record EconomyCarriageRequirement(
        int seatsLimit,
        int seatWeightKg,
        int toiletWeightKg,
        int numberOfToilets,
        int baseCarriageWeightKg,
        List<Seat> seats,
        boolean hasBioToilets
) implements CarriageRequirement {
}
