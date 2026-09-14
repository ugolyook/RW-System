package com.sveta.factory.carriageFactory;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.CoupeCarriage;
import com.sveta.dto.CarriageRequirements;

public record CoupeCarriageFactory(CarriageRequirements reqs) implements CarriagesFactory {
    @Override
    public Carriage createCarriage() {
        return new CoupeCarriage(
                reqs.getCoupeLimit(),
                reqs.getCoupeWeightKg(),
                reqs.getCoupeBaseCarriageWeightKg(),
                reqs.isCoupeGenderSpecific(),
                reqs.isCoupePetFriendly()
        );
    }
}
