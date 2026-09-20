package com.sveta.factory.carriage.coupe;

import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.CoupeCarriage;
import com.sveta.factory.carriage.CarriageRequirement;
import com.sveta.train.carriage.passenger.models.Seat;
import com.sveta.train.carriage.passenger.models.SeatType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CoupeCarriageFactory implements com.sveta.factory.carriage.CarriageFactory {
    @Override
    public Carriage build(CarriageRequirement req) {
        if (Objects.isNull(req) || !canBuild(req)) {
            return null;
        }

        var coupeReq = (CoupeCarriageRequirement) req;
        var coupeRequirements = coupeReq.coupeRequirements();
        var coupes = buildCoupes(coupeRequirements);

        return buildCoupeCarriage(coupes, coupeReq);
    }

    private List<CoupeCarriage.Coupe> buildCoupes(List<CoupeRequirement> coupeRequirements) {
        List<CoupeCarriage.Coupe> result = new ArrayList<>();
        int currentSeatNumber = 1;

        for (CoupeRequirement req : coupeRequirements) {
            CoupeCarriage.Coupe coupe = new CoupeCarriage.Coupe();

            for (int i = 0; i < CoupeCarriage.Coupe.SEATS_PER_COUPE; i++) {
                SeatType type = (i % 2 == 0) ? SeatType.LOWER : SeatType.UPPER;
                coupe.seats.add(new Seat(currentSeatNumber++, type, false));
            }

            result.add(coupe);
        }

        return result;
    }

    private CoupeCarriage buildCoupeCarriage(List<CoupeCarriage.Coupe> coupes, CoupeCarriageRequirement coupeReq) {
        return new CoupeCarriage(
                coupes,
                coupeReq.carriageBaseWeightInKg()
        );
    }

    @Override
    public boolean canBuild(CarriageRequirement req) {
        return req instanceof CoupeCarriageRequirement;
    }
}