package com.sveta.factory.carriage.toUpdate;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.SeatedCarriage;
import com.sveta.factory.carriage.CarriageFactory;
import com.sveta.factory.carriage.CarriageRequirement;

public record SeatedCarriageFactory(CarriageRequirements reqs) implements CarriageFactory {
    @Override
    public Carriage build(CarriageRequirement req) {
        return new SeatedCarriage(reqs.getSeatedBicycleSpots(), reqs.getSeatedPitchSm());
    }

    @Override
    public boolean canBuild(CarriageRequirement req) {
        return false;
    }
}
