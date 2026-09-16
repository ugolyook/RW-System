package com.sveta.factory.carriage.dining;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.DiningCarriage;
import com.sveta.factory.carriage.CarriageFactory;
import com.sveta.factory.carriage.CarriageRequirement;
import com.sveta.factory.carriage.coupe.CoupeCarriageRequirement;
import com.sveta.factory.carriage.toUpdate.CarriageRequirements;

import java.util.Objects;

public class DiningCarriageFactory implements CarriageFactory {
    @Override
    public Carriage build(CarriageRequirement req) {
        if (Objects.isNull(req) || canBuild(req)) {
            return null;
        }

        var diningReq = (DiningCarriageRequirement) req;

        return buildDiningCarriage(diningReq);
    }

    private Carriage buildDiningCarriage(DiningCarriageRequirement diningReq) {
        DiningCarriage diningCarriage = new DiningCarriage(
                diningReq.seatsLimit(),
                diningReq.kitchenWeightKg(),
                diningReq.seatWeightKg(),
                diningReq.baseCarriageWeightKg(),
                diningReq.hasHotKitchen(),
                diningReq.deliveryToTheRoom()
        );

        if (diningReq.seats() != null) {
            diningCarriage.seats = diningReq.seats();
        }
        diningCarriage.food = diningReq.food();

        return diningCarriage;
    }


    @Override
    public boolean canBuild(CarriageRequirement req) {
        return !(req instanceof DiningCarriageRequirement);
    }
}
