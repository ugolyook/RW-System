package com.sveta.factory.carriage.coupe;

public record CoupeRequirement(
        int weightInKg,
        int seatNumbers,
        boolean coupeGenderSpecific,
        boolean coupePetFriendly,
        boolean hasElectricity,
        boolean hasWifi
) {
}
