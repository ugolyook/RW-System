package com.sveta.factory.carriage.toUpdate;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.DiningCarriage;
import com.sveta.factory.carriage.CarriageFactory;
import com.sveta.factory.carriage.CarriageRequirement;

public record DiningCarriageFactory(CarriageRequirements reqs) implements CarriageFactory {
    @Override
    public Carriage build(CarriageRequirement req) {
        return new DiningCarriage(reqs.isDiningHotKitchen(), reqs.isDiningDelivery());
    }

    @Override
    public boolean canBuild(CarriageRequirement req) {
        return true;
    }
}
