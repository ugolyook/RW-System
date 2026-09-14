package com.sveta.dto;

public record LocomotiveRequirements(int requiredPowerInKw,
                                     int requiredTraction,
                                     int totalWeightInKg,
                                     boolean isElectric) {
}