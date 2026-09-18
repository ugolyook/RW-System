package com.sveta.factory.carriage.coupe;

public record CoupeRequirement(
        int weightInKg,
        int seatNumbers,
        boolean hasElectricity,
        boolean hasWifi
) {
}
