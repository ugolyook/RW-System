package com.sveta.factory.carriage.coupe;

import com.sveta.factory.carriage.CarriageRequirement;

import java.util.List;

public record CoupeCarriageRequirement(
        List<CoupeRequirement> coupeRequirements,
        boolean hasWC,
        boolean hasBoiledWater,
        int carriageBaseWeightInKg
) implements CarriageRequirement {
}
