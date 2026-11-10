package com.sveta.init;

import com.sveta.TestDataFactory;
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
import com.sveta.train.carriage.passenger.models.Food;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InitData {
    private InitData() {
    }

    private static final int CARRIAGE_LIMIT = 18;

    private static final Station MINSK = new Station("Minsk", 2200001);
    private static final Station MINSK_PASS = new Station("Minsk-Passenger", 2200020);
    private static final Station MOGILEV = new Station("Mogilev", 2200030);
    private static final Station MOGILEV_CENTRAL = new Station("Mogilev Central", 2200060);
    private static final Station BARANOVICHI = new Station("Baranovichi", 2100010);
    private static final Station BREST = new Station("Brest", 2100050);
    private static final Station OSIPOVICHI = new Station("Osipovichi", 2200040);
    private static final Station GOMEL = new Station("Gomel", 2300070);

    private static Route routeMinskMogilev() {
        return new Route(List.of(MINSK, MINSK_PASS, MOGILEV, MOGILEV_CENTRAL), Directions.FORWARD);
    }

    private static Route routeMinskBrest() {
        return new Route(List.of(MINSK, BARANOVICHI, BREST), Directions.FORWARD);
    }

    private static Route routeMinskGomel() {
        return new Route(List.of(MINSK, OSIPOVICHI, GOMEL), Directions.FORWARD);
    }

    public static List<Train> createTrains() {
        BaseCarriageFactory carriageFactory = new BaseCarriageFactory(List.of(
                new CoupeCarriageFactory(),
                new SeatedCarriageFactory(),
                new DiningCarriageFactory(),
                new EconomyCarriageFactory()));
        PassengerTrainFactory trainFactory =
                new PassengerTrainFactory(CARRIAGE_LIMIT, new LocomotiveFactory());

        List<Train> trains = new ArrayList<>();
        trains.add(trainFactory.createTrain(
                carriageFactory.createAll(fullComposition())));
        trains.add(trainFactory.createTrain(
                carriageFactory.createAll(seatedComposition())));
        trains.add(trainFactory.createTrain(
                carriageFactory.createAll(economyComposition())));
        return trains;
    }

    private static List<CarriageRequirement> fullComposition() {
        var coupe1 = CoupeRequirement.builder()
                .weightInKg(1).seatNumbers(4).hasElectricity(false).hasWifi(false).build();
        var coupe2 = CoupeRequirement.builder()
                .weightInKg(2).seatNumbers(4).hasElectricity(true).hasWifi(false).build();

        return List.of(
                new CoupeCarriageRequirement(List.of(coupe1, coupe1, coupe1, coupe1, coupe2, coupe2), 50000),
                new SeatedCarriageRequirement(48, 4, 10000),
                new DiningCarriageRequirement(32, 45000, List.of(Food.BORSCH, Food.PASTA), true, false),
                new EconomyCarriageRequirement(54, 48000, true));
    }

    private static List<CarriageRequirement> seatedComposition() {
        return List.of(
                new SeatedCarriageRequirement(48, 4, 10000),
                new SeatedCarriageRequirement(48, 4, 10000),
                new DiningCarriageRequirement(32, 45000, List.of(Food.BORSCH), true, false));
    }

    private static List<CarriageRequirement> economyComposition() {
        return List.of(
                new EconomyCarriageRequirement(54, 48000, true),
                new EconomyCarriageRequirement(54, 48000, false));
    }

    public static List<TrainRun> createTrainRuns(List<Train> trains) {
        List<TrainRun> runs = new ArrayList<>();
        runs.add(run(trains.get(0), routeMinskMogilev(), LocalDateTime.of(2026, 9, 20, 14, 30), true));
        runs.add(run(trains.get(0), routeMinskMogilev(), LocalDateTime.of(2026, 9, 21, 14, 30), true));
        runs.add(run(trains.get(1), routeMinskBrest(), LocalDateTime.of(2026, 9, 20, 8, 15), false));
        runs.add(run(trains.get(2), routeMinskGomel(), LocalDateTime.of(2026, 9, 22, 19, 0), false));
        return runs;
    }

    private static TrainRun run(Train train, Route route, LocalDateTime departure, boolean express) {
        return TrainRun.builder()
                .train(train)
                .route(route)
                .departureTime(departure)
                .isExpress(express)
                .build();
    }
}