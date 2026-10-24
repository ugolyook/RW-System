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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

public class TestDataFactory {
    private static final Logger log = LoggerFactory.getLogger(TestDataFactory.class);

    private static final int DEFAULT_CARRIAGE_LIMIT = 18;

    public static BaseCarriageFactory createBaseCarriageFactory() {
        log.debug("Initializing BaseCarriageFactory with available carriage factories.");
        return new BaseCarriageFactory(List.of(
                new CoupeCarriageFactory(),
                new SeatedCarriageFactory(),
                new DiningCarriageFactory(),
                new EconomyCarriageFactory()
        ));
    }

    public static PassengerTrainFactory createTrainFactory() {
        log.debug("Initializing PassengerTrainFactory with size limit: {} and LocomotiveFactory.", DEFAULT_CARRIAGE_LIMIT);
        return new PassengerTrainFactory(DEFAULT_CARRIAGE_LIMIT, new LocomotiveFactory());
    }

    public static List<Carriage> createCarriages(BaseCarriageFactory factory) {
        log.info("Preparing test carriage requirements (Coupe, Seated, Dining, Economy)...");

        var coupeReq1 = CoupeRequirement.builder()
                .weightInKg(1)
                .seatNumbers(4)
                .hasElectricity(false)
                .hasWifi(false)
                .build();

        var coupeReq2 = CoupeRequirement.builder()
                .weightInKg(2)
                .seatNumbers(4)
                .hasElectricity(true)
                .hasWifi(false)
                .build();

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

        List<Carriage> carriages = factory.createAll(List.of(coupeCarriageReq1, seatedReq, diningReq, economyReq));
        log.info("Successfully created size test carriages via BaseCarriageFactory: {}", carriages.size());

        return carriages;
    }

    public static TrainRun createTrainRun(Train train) {
        log.info("Creating test train run for train along route Minsk -> Mogilev...(train № {})", train.getTrainNumber());

        Station minsk = new Station("Minsk", 2200001);
        Station minskPass = new Station("Minsk-Passenger", 2200020);
        Station mogilev = new Station("Mogilev", 2200030);
        Station mogilevCentral = new Station("Mogilev Central", 2200060);

        Route route = new Route(List.of(minsk, minskPass, mogilev, mogilevCentral), Directions.FORWARD);
        LocalDateTime departure = LocalDateTime.of(2026, 9, 20, 14, 30);

        return TrainRun.builder()
                .train(train)
                .route(route)
                .departureTime(departure)
                .isExpress(true)
                .build();
    }
}