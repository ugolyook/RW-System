package com.sveta.factory.carriage.economy;

import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.EconomyCarriage;
import com.sveta.factory.carriage.CarriageFactory;
import com.sveta.factory.carriage.CarriageRequirement;
import com.sveta.train.carriage.passenger.models.Seat;
import com.sveta.train.carriage.passenger.models.SeatType;

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
        EconomyCarriage carriage = new EconomyCarriage(
                ecoReq.seatsLimit(),
                ecoReq.baseCarriageWeightKg(),
                ecoReq.hasBioToilets()
        );

        for (int i = 1; i <= ecoReq.seatsLimit(); i++) {
            SeatType type = (i % 2 == 0) ? SeatType.UPPER : SeatType.LOWER;
            carriage.seats.add(new Seat(i, type, false));
        }

        return carriage;
    }

    @Override
    public boolean canBuild(CarriageRequirement req) {
        return req instanceof EconomyCarriageRequirement;
    }
}