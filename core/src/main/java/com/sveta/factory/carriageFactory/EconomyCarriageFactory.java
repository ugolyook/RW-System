package com.sveta.factory.carriageFactory;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.EconomyCarriage;
import com.sveta.dto.CarriageRequirements;

public record EconomyCarriageFactory(CarriageRequirements reqs) implements CarriagesFactory {
    @Override
    public Carriage createCarriage() {
        return new EconomyCarriage(reqs.isEconomyBioToilets());
    }
}
