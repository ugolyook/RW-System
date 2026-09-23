package com.sveta.factory.carriage.seated;

import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.SeatedCarriage;
import com.sveta.factory.carriage.CarriageRequirement;
import com.sveta.factory.carriage.CarriageFactory;
import com.sveta.train.carriage.passenger.models.Seat;
import com.sveta.train.carriage.passenger.models.SeatType;

import java.util.Objects;

public class SeatedCarriageFactory implements CarriageFactory {
    @Override
    public Carriage build(CarriageRequirement req) {
        if (Objects.isNull(req) || !canBuild(req)) {
            return null;
        }
        var seatedReq = (SeatedCarriageRequirement) req;

        return createCarriage(seatedReq);
    }

    @Override
    public boolean canBuild(CarriageRequirement req) {
        return req instanceof SeatedCarriageRequirement;
    }

    private SeatedCarriage createCarriage(SeatedCarriageRequirement seatedReq) {
        SeatedCarriage carriage = new SeatedCarriage(
                seatedReq.placeNumbers(),
                seatedReq.numberOfBicyclePlaces() > 0,
                seatedReq.baseCarriageWeightKg()
        );

        for (int i = 1; i <= seatedReq.placeNumbers(); i++) {
            carriage.seats.add(new Seat(i, SeatType.LOWER,false));
        }

        return carriage;
    }
}
