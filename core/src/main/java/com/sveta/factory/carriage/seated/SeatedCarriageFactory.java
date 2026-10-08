package com.sveta.factory.carriage.seated;

import com.sveta.exeptions.RequirementExceptions;
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
        if (seatedReq.placeNumbers() < 0) {
            throw new RequirementExceptions.NegativeSizeException();
        }

        SeatedCarriage carriage = new SeatedCarriage(
                seatedReq.placeNumbers(),
                seatedReq.baseCarriageWeightKg()
        );

        int regularSeatsCount = seatedReq.placeNumbers() - seatedReq.bicycleSpotsCount();

        for (int i = 1; i <= regularSeatsCount; i++) {
            carriage.getSeats().add(new Seat(i, SeatType.LOWER));
        }

        for (int i = regularSeatsCount + 1; i <= seatedReq.placeNumbers(); i++) {
            carriage.getSeats().add(new Seat(i, SeatType.BICYCLE));
        }

        return carriage;
    }
}