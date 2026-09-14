package com.sveta.factory.carriageFactory;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.DiningCarriage;
import com.sveta.dto.CarriageRequirements;

public record DiningCarriageFactory(CarriageRequirements reqs) implements CarriagesFactory {
    @Override
    public Carriage createCarriage() {
        return new DiningCarriage(reqs.isDiningHotKitchen(), reqs.isDiningDelivery());
    }
}
