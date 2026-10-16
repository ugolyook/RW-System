package com.sveta.factory.carriage.economy;

import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.EconomyCarriage;
import com.sveta.factory.carriage.CarriageFactory;
import com.sveta.factory.carriage.CarriageRequirement;
import com.sveta.train.carriage.passenger.models.Seat;
import com.sveta.train.carriage.passenger.models.SeatType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class EconomyCarriageFactory implements CarriageFactory {
    private static final Logger log = LoggerFactory.getLogger(EconomyCarriageFactory.class);
    @Override
    public Carriage build(CarriageRequirement req) {
        if (Objects.isNull(req) || !canBuild(req)) {
            return null;
        }

        var ecoReq = (EconomyCarriageRequirement) req;
        log.info("Start to build economy");
        return buildEconomyCarriage(ecoReq);
    }

    private Carriage buildEconomyCarriage(EconomyCarriageRequirement ecoReq) {
        EconomyCarriage carriage = EconomyCarriage.builder()
                .seatsLimit(ecoReq.seatsLimit())
                .hasBioToilets(ecoReq.hasBioToilets())
                .build();

        for (int i = 1; i <= ecoReq.seatsLimit(); i++) {
            SeatType type = (i % 2 == 0) ? SeatType.UPPER : SeatType.LOWER;
            carriage.getSeats().add(new Seat(i, type));
        }

        return carriage;
    }

    @Override
    public boolean canBuild(CarriageRequirement req) {
        return req instanceof EconomyCarriageRequirement;
    }
}