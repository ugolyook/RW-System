package com.sveta.factory.locomotive;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Builder
@Accessors(fluent = true)
public class LocomotiveRequirements {
    private final int requiredPowerInKw;
    private final int requiredTraction;
    private final int totalWeightInKg;
    private final boolean isElectric;
}