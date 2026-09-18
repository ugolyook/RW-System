package com.sveta.factory.carriage.dining;

import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.DiningCarriage;
import com.sveta.factory.carriage.CarriageFactory;
import com.sveta.factory.carriage.CarriageRequirement;

import java.util.Objects;

public class DiningCarriageFactory implements CarriageFactory {
    @Override
    public Carriage build(CarriageRequirement req) {
        if (Objects.isNull(req) || !canBuild(req)) {
            return null;
        }

        var diningReq = (DiningCarriageRequirement) req;

        return buildDiningCarriage(diningReq);
    }

    private Carriage buildDiningCarriage(DiningCarriageRequirement diningReq) {
        DiningCarriage diningCarriage = new DiningCarriage(
                diningReq.seatsLimit(),
                diningReq.baseCarriageWeightKg(),
                diningReq.hasHotKitchen(),
                diningReq.deliveryToTheRoom()
        );

        diningCarriage.food = diningReq.food();

        return diningCarriage;
    }

    @Override
    public boolean canBuild(CarriageRequirement req) {
        return req instanceof DiningCarriageRequirement;
    }
}