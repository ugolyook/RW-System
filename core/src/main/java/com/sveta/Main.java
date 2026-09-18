package com.sveta;

import com.sveta.route.Directions;
import com.sveta.route.Route;
import com.sveta.route.Station;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.models.Food;
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
import com.sveta.train.formatter.BaseTrainInfoFormatter;
import com.sveta.train.formatter.TrainInfoFormatter;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    private final static int DEFAULT_CARRIAGE_LIMIT_PER_TRAIN = 18;

    private final PassengerTrainFactory passengerTrainFactory;
    private final BaseCarriageFactory baseCarriageFactory;
    private final TrainInfoFormatter trainInfoFormatter;

    static void main(String[] args) {
        var main = new Main();
        main.start();
    }

    public Main() {
        passengerTrainFactory = configureTrainFactory();
        baseCarriageFactory = configureBaseCarriageFactory();
        trainInfoFormatter = new BaseTrainInfoFormatter();
    }

    private void start() {
        var carriages = createCarriages();
        var train = passengerTrainFactory.createTrain(carriages);
        var trainOutputString = trainInfoFormatter.format(train);
        System.out.println("We build a first train!");
        System.out.println(trainOutputString);
        createTrainRun();
    }

    private static void createTrainRun() {
        Station minsk = new Station("Minsk", 2200001);
        Station minskPassenger = new Station("Minsk-Passenger", 2200020);
        Station mogilev = new Station("Mogilev", 2200030);
        Station mogilevCentral = new Station("Mogilev Central", 2200060);

        List<Station> stops = List.of(minsk,minskPassenger,mogilev,mogilevCentral);
        Route route = new Route(stops, Directions.FORWARD);

        LocalDateTime departure = LocalDateTime.of(2026, 9, 20, 14, 30);

        System.out.println("Route: " + route.getDirection());
        System.out.println("Train stops:");
        for (Station station : route.getStops()) {
            System.out.println(" - " + station);
        }
        System.out.println("Departure: " + departure);
    }

    private List<Carriage> createCarriages() {
        var coupeReq1 = new CoupeRequirement(111, 62, false, false);
        var coupeReq2 = new CoupeRequirement(11, 62, true, false);
        var coupeReq3 = new CoupeRequirement(11, 62, false, false);

        CarriageRequirement coupeCarriageReq1 = new CoupeCarriageRequirement(
                List.of(coupeReq1, coupeReq1, coupeReq1, coupeReq1, coupeReq2, coupeReq2, coupeReq2, coupeReq3),
                50000
        );

        CarriageRequirement coupeCarriageReq2 = new CoupeCarriageRequirement(
                List.of(coupeReq1, coupeReq1, coupeReq2, coupeReq3, coupeReq3, coupeReq3, coupeReq3),
                50000
        );

        var allRequirement = getCarriageRequirements(coupeCarriageReq1, coupeCarriageReq2);

        return baseCarriageFactory.createAll(allRequirement);
    }

    private static List<CarriageRequirement> getCarriageRequirements(CarriageRequirement coupeCarriageReq1, CarriageRequirement coupeCarriageReq2) {
        CarriageRequirement seatedCarriageReq = new SeatedCarriageRequirement(
                48,
                2,
                10000
        );

        CarriageRequirement diningCarriageReq = new DiningCarriageRequirement(
                32,
                45000,
                Food.BORSCH,
                true,
                false
        );

        CarriageRequirement economyCarriageReq = new EconomyCarriageRequirement(
                54,
                48000,
                true
        );

        return List.of(
                coupeCarriageReq1,
                coupeCarriageReq2,
                seatedCarriageReq,
                diningCarriageReq,
                economyCarriageReq
        );
    }

    private BaseCarriageFactory configureBaseCarriageFactory() {
        var coupeFactory = new CoupeCarriageFactory();
        var seatedFactory = new SeatedCarriageFactory();
        var diningFactory = new DiningCarriageFactory();
        var economyFactory = new EconomyCarriageFactory();

        var allCarriageFactories = List.of(
                coupeFactory,
                seatedFactory,
                diningFactory,
                economyFactory
        );

        return new BaseCarriageFactory(allCarriageFactories);
    }

    private PassengerTrainFactory configureTrainFactory() {
        var locomotiveFactory = new LocomotiveFactory();
        return new PassengerTrainFactory(DEFAULT_CARRIAGE_LIMIT_PER_TRAIN, locomotiveFactory);
    }
}