package com.sveta.factory.carriage.coupe;

import com.sveta.factory.carriage.CarriageRequirement;

import java.util.List;

public record CoupeCarriageRequirement(
        List<CoupeRequirement> coupeRequirements,
        int carriageBaseWeightInKg
) implements CarriageRequirement {
    @Override
    public int getWeightInKg() {
        return carriageBaseWeightInKg;
    }
}