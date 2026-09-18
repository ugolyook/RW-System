package com.sveta.factory.carriage.seated;

import com.sveta.factory.carriage.CarriageRequirement;

public record SeatedCarriageRequirement(
        int placeNumbers,
        int numberOfBicyclePlaces,
        int baseCarriageWeightKg
) implements CarriageRequirement {
}