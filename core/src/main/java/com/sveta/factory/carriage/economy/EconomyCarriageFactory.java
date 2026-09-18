package com.sveta.factory.carriage.economy;

import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.EconomyCarriage;
import com.sveta.factory.carriage.CarriageFactory;
import com.sveta.factory.carriage.CarriageRequirement;

import java.util.Objects;

public class EconomyCarriageFactory implements CarriageFactory {
    @Override
    public Carriage build(CarriageRequirement req) {
        if (Objects.isNull(req) || !canBuild(req)) {
            return null;
        }

        var ecoReq = (EconomyCarriageRequirement) req;
        return buildEconomyCarriage(ecoReq);
    }

    private Carriage buildEconomyCarriage(EconomyCarriageRequirement ecoReq) {
        return new EconomyCarriage(
                ecoReq.seatsLimit(),
                ecoReq.baseCarriageWeightKg(),
                ecoReq.hasBioToilets()
        );
    }

    @Override
    public boolean canBuild(CarriageRequirement req) {
        return req instanceof EconomyCarriageRequirement;
    }
}