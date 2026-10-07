package com.sveta.factory.locomotive;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LocomotiveRequirements {
    private final int requiredPower;
    private final int requiredTractionKn;
    private final int totalWeightKg;
    private final boolean isElectric;
}