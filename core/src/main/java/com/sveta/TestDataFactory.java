package com.sveta;

import com.sveta.factory.carriage.BaseCarriageFactory;
import com.sveta.factory.carriage.CarriageRequirement;
import com.sveta.factory.carriage.coupe.CoupeCarriageFactory;
import com.sveta.factory.carriage.coupe.CoupeCarriageRequirement;
import com.sveta.factory.carriage.coupe.CoupeRequirement;
import com.sveta.factory.carriage.dining.DiningCarriageFactory;
import com.sveta.factory.carriage.dining.DiningCarriageRequirement;
import com.sveta.factory.carriage.economy.EconomyCarriageFactory;
import com.sveta.factory.carriage.economy.EconomyCarriageRequirement;
import com.sveta.factory.carriage.seated.SeatedCarriageFactory;
import com.sveta.factory.carriage.seated.SeatedCarriageRequirement;
import com.sveta.factory.locomotive.LocomotiveFactory;
import com.sveta.factory.train.PassengerTrainFactory;
import com.sveta.route.Directions;
import com.sveta.route.Route;
import com.sveta.route.Station;
import com.sveta.route.TrainRun;
import com.sveta.train.Train;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.models.Food;

import java.time.LocalDateTime;
import java.util.List;

public class TestDataFactory {

    private static final int DEFAULT_CARRIAGE_LIMIT = 18;

    public static BaseCarriageFactory createBaseCarriageFactory() {
        return new BaseCarriageFactory(List.of(
                new CoupeCarriageFactory(),
                new SeatedCarriageFactory(),
                new DiningCarriageFactory(),
                new EconomyCarriageFactory()
        ));
    }

    public static PassengerTrainFactory createTrainFactory() {
        return new PassengerTrainFactory(DEFAULT_CARRIAGE_LIMIT, new LocomotiveFactory());
    }

    public static List<Carriage> createCarriages(BaseCarriageFactory factory) {
        var coupeReq1 = new CoupeRequirement(1, 4, false, false);
        var coupeReq2 = new CoupeRequirement(2, 4, true, false);

        CarriageRequirement coupeCarriageReq1 = new CoupeCarriageRequirement(
                List.of(coupeReq1, coupeReq1, coupeReq1, coupeReq1, coupeReq2, coupeReq2),
                50000
        );

        CarriageRequirement seatedReq = new SeatedCarriageRequirement(
                48, 4, 10000
        );
        CarriageRequirement diningReq = new DiningCarriageRequirement(
                32, 45000, List.of(Food.BORSCH, Food.PASTA),
                true, false
        );
        CarriageRequirement economyReq = new EconomyCarriageRequirement(
                54, 48000, true
        );

        return factory.createAll(List.of(coupeCarriageReq1, seatedReq, diningReq, economyReq));
    }

    public static TrainRun createTrainRun(Train train) {
        Station minsk = new Station("Minsk", 2200001);
        Station minskPass = new Station("Minsk-Passenger", 2200020);
        Station mogilev = new Station("Mogilev", 2200030);
        Station mogilevCentral = new Station("Mogilev Central", 2200060);

        Route route = new Route(List.of(minsk, minskPass, mogilev, mogilevCentral), Directions.FORWARD);
        LocalDateTime departure = LocalDateTime.of(2026, 9, 20, 14, 30);

        return new TrainRun(train, route, departure, true);
    }
}