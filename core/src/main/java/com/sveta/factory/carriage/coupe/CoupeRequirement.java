package com.sveta.factory.carriage.coupe;

import lombok.Builder;

@Builder
public record CoupeRequirement(
        int weightInKg,
        int seatNumbers,
        boolean hasElectricity,
        boolean hasWifi
) {
}