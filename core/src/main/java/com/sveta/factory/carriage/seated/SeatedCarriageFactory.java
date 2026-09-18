package com.sveta.factory.carriage.seated;

import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.SeatedCarriage;
import com.sveta.factory.carriage.CarriageRequirement;
import com.sveta.factory.carriage.CarriageFactory;

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

    private SeatedCarriage createCarriage(SeatedCarriageRequirement seatedReq) {
        return new SeatedCarriage(
                seatedReq.placeNumbers(),
                seatedReq.numberOfBicyclePlaces() > 0,
                seatedReq.baseCarriageWeightKg()
        );
    }

    @Override
    public boolean canBuild(CarriageRequirement req) {
        return req instanceof SeatedCarriageRequirement;
    }
}