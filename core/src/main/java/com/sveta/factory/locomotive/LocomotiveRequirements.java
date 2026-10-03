package com.sveta.factory.locomotive;

public record LocomotiveRequirements(
        int requiredPowerInKw,
        int requiredTraction,
        int totalWeightInKg,
        boolean isElectric
) {
}