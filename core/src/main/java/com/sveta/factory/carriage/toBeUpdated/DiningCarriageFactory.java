package com.sveta.factory.carriage.toBeUpdated;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.DiningCarriage;
import com.sveta.factory.carriage.CarriageFactory;

public record DiningCarriageFactory(CarriageRequirements reqs) implements CarriageFactory {
    @Override
    public Carriage build(CarriageRequirements req) {
        return new DiningCarriage(reqs.isDiningHotKitchen(), reqs.isDiningDelivery());
    }
}
