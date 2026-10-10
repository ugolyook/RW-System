package com.sveta.factory.locomotive;

import lombok.Builder;
import lombok.experimental.Accessors;

@Builder
@Accessors(fluent = true)
public record LocomotiveRequirements(
        int requiredPowerInKw,
        int requiredTraction,
        int totalWeightInKg,
        boolean isElectric) {
}