package com.sveta.factory.carriage.economy;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.EconomyCarriage;
import com.sveta.factory.carriage.CarriageFactory;
import com.sveta.factory.carriage.CarriageRequirement;
import com.sveta.factory.carriage.toUpdate.CarriageRequirements;

import java.util.Objects;

public class EconomyCarriageFactory implements CarriageFactory {
    @Override
    public Carriage build(CarriageRequirement req) {
        if (Objects.isNull(req) || canBuild(req)) {
            return null;
        }

        var ecoReq = (EconomyCarriageRequirement) req;
        return buildEconomyCarriage(ecoReq);
    }

    private Carriage buildEconomyCarriage(EconomyCarriageRequirement ecoReq) {
        EconomyCarriage carriage = new EconomyCarriage(
                ecoReq.seatsLimit(),
                ecoReq.seatWeightKg(),
                ecoReq.toiletWeightKg(),
                ecoReq.numberOfToilets(),
                ecoReq.baseCarriageWeightKg(),
                ecoReq.hasBioToilets()
        );

        if (ecoReq.seats() != null) {
            carriage.seats = ecoReq.seats();
        }

        return carriage;
    }

    @Override
    public boolean canBuild(CarriageRequirement req) {
        return !(req instanceof EconomyCarriageRequirement);
    }
}
