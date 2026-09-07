package com.sveta.dto;

public class LocomotiveRequirements {
    private final int requiredPower;
    private final int requiredTraction;
    private final int totalWeight;
    private final boolean isElectric;

    public LocomotiveRequirements(int requiredPower, int requiredTraction,
                                  int totalWeight, boolean isElectric) {
        this.requiredPower = requiredPower;
        this.requiredTraction = requiredTraction;
        this.totalWeight = totalWeight;
        this.isElectric = isElectric;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public int getRequiredTraction() {
        return requiredTraction;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public int getRequiredPower() {
        return requiredPower;
    }
}
