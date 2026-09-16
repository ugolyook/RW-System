package com.sveta.factory.carriage.toUpdate;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.EconomyCarriage;
import com.sveta.factory.carriage.CarriageFactory;
import com.sveta.factory.carriage.CarriageRequirement;

public record EconomyCarriageFactory(CarriageRequirements reqs) implements CarriageFactory {
    @Override
    public Carriage build(CarriageRequirement req) {
        return new EconomyCarriage(reqs.isEconomyBioToilets());
    }

    @Override
    public boolean canBuild(CarriageRequirement req) {
        return true;
    }
}
