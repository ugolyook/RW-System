package com.sveta.factory.carriageFactory;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.SeatedCarriage;
import com.sveta.dto.CarriageRequirements;

public record SeatedCarriageFactory(CarriageRequirements reqs) implements CarriagesFactory {
    @Override
    public Carriage createCarriage() {
        return new SeatedCarriage(reqs.getSeatedBicycleSpots(), reqs.getSeatedPitchSm());
    }
}
