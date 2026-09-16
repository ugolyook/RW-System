package com.sveta.factory.carriage.coupe;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.CoupeCarriage;
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
                .map(c -> new CoupeCarriage.Coupe(0, 0)) // to be updated
                .toList();
    }

    private CoupeCarriage buildCoupeCarriage(List<CoupeCarriage.Coupe> coupes, CoupeCarriageRequirement coupeReq) {
        var coupeRequirements = coupeReq.coupeRequirements();
        var coupeWeightKg = coupeRequirements.stream()
                .mapToInt(CoupeRequirement::seatNumbers)
                .sum() / Math.max(coupes.size(), 1);
        var allowsGenderSpecific = coupeRequirements.stream()
                .anyMatch(CoupeRequirement::coupeGenderSpecific);
        var hasPetFriendly = coupeRequirements.stream()
                .anyMatch(CoupeRequirement::coupePetFriendly);

        return new CoupeCarriage(
                coupes,
                coupeWeightKg,
                coupeReq.carriageBaseWeightInKg(),
                allowsGenderSpecific,
                hasPetFriendly
        );
    }

    @Override
    public boolean canBuild(CarriageRequirement req) {
        return !(req instanceof CoupeCarriageRequirement);
    }
}