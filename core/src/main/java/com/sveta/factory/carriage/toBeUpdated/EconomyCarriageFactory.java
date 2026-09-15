package com.sveta.factory.carriage.toBeUpdated;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.EconomyCarriage;
import com.sveta.factory.carriage.CarriageFactory;

public record EconomyCarriageFactory(CarriageRequirements reqs) implements CarriageFactory {
    @Override
    public Carriage build(CarriageRequirements req) {
        return new EconomyCarriage(reqs.isEconomyBioToilets());
    }
}
