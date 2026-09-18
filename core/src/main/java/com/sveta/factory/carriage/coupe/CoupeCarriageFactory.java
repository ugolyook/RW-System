package com.sveta.factory.carriage.coupe;

import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.CoupeCarriage;
import com.sveta.factory.carriage.CarriageRequirement;

import java.util.List;
import java.util.Objects;

public class CoupeCarriageFactory implements com.sveta.factory.carriage.CarriageFactory {
    @Override
    public Carriage build(CarriageRequirement req) {
        if (Objects.isNull(req) || canBuild(req)) {
            return null;
        }

        var coupeReq = (CoupeCarriageRequirement) req;
        var coupeRequirements = coupeReq.coupeRequirements();
        var coupes = buildCoupes(coupeRequirements);

        return buildCoupeCarriage(coupes, coupeReq);
    }

    private List<CoupeCarriage.Coupe> buildCoupes(List<CoupeRequirement> coupeRequirements) {
        return coupeRequirements.stream()
                .map(c -> new CoupeCarriage.Coupe())
                .toList();
    }

    private CoupeCarriage buildCoupeCarriage(List<CoupeCarriage.Coupe> coupes, CoupeCarriageRequirement coupeReq) {
        return new CoupeCarriage(
                coupes,
                coupeReq.carriageBaseWeightInKg()
        );
    }

    @Override
    public boolean canBuild(CarriageRequirement req) {
        return !(req instanceof CoupeCarriageRequirement);
    }
}