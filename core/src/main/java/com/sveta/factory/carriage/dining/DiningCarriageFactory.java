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
        return DiningCarriage.builder()
                .seatsLimit(diningReq.seatsLimit())
                .hasHotKitchen(diningReq.hasHotKitchen())
                .deliveryToTheRoom(diningReq.deliveryToTheRoom())
                .food(diningReq.food())
                .build();
    }

    @Override
    public boolean canBuild(CarriageRequirement req) {
        return req instanceof DiningCarriageRequirement;
    }
}