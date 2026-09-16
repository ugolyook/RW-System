package com.sveta.factory.carriage.seated;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.SeatedCarriage;
import com.sveta.factory.carriage.CarriageRequirement;
import com.sveta.factory.carriage.CarriageFactory;

import java.util.Objects;

public class SeatedCarriageFactory implements CarriageFactory {
    @Override
    public Carriage build(CarriageRequirement req) {
        if (Objects.isNull(req) || canBuild(req)) {
            return null;
        }
        var seatedReq = (SeatedCarriageRequirement) req;

        return createCarriage(seatedReq);
    }

    private SeatedCarriage createCarriage(SeatedCarriageRequirement seatedReq) {
        return new SeatedCarriage( // to be updated
                seatedReq.placeNumbers(),
                0,
                0.0
        );
    }

    @Override
    public boolean canBuild(CarriageRequirement req) {
        return !(req instanceof SeatedCarriageRequirement);
    }
}
