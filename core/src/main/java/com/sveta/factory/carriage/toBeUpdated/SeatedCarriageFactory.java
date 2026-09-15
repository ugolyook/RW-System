package com.sveta.factory.carriage.toBeUpdated;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.SeatedCarriage;
import com.sveta.factory.carriage.CarriageFactory;

public record SeatedCarriageFactory(CarriageRequirements reqs) implements CarriageFactory {
    @Override
    public Carriage build(CarriageRequirements req) {
        return new SeatedCarriage(reqs.getSeatedBicycleSpots(), reqs.getSeatedPitchSm());
    }
}
