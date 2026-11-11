package com.sveta.init.data;

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
import com.sveta.train.Train;
import com.sveta.train.carriage.passenger.models.Food;

import java.util.ArrayList;
import java.util.List;

public class InitTrains {
    private static final int CARRIAGE_LIMIT = 18;

    public static final int FULL_TRAIN = 0;
    public static final int SEATED_TRAIN = 1;
    public static final int ECONOMY_TRAIN = 2;

    public List<Train> create() {
        BaseCarriageFactory carriageFactory = createCarriageFactory();
        PassengerTrainFactory trainFactory = createTrainFactory();

        List<Train> trains = new ArrayList<>();
        trains.add(trainFactory.createTrain(carriageFactory.createAll(fullComposition())));
        trains.add(trainFactory.createTrain(carriageFactory.createAll(seatedComposition())));
        trains.add(trainFactory.createTrain(carriageFactory.createAll(economyComposition())));
        return trains;
    }

    private BaseCarriageFactory createCarriageFactory() {
        return new BaseCarriageFactory(List.of(
                new CoupeCarriageFactory(),
                new SeatedCarriageFactory(),
                new DiningCarriageFactory(),
                new EconomyCarriageFactory()));
    }

    private PassengerTrainFactory createTrainFactory() {
        return new PassengerTrainFactory(CARRIAGE_LIMIT, new LocomotiveFactory());
    }

    private List<CarriageRequirement> fullComposition() {
        var coupe1 = CoupeRequirement.builder()
                .weightInKg(1).seatNumbers(4).hasElectricity(false).hasWifi(false).build();
        var coupe2 = CoupeRequirement.builder()
                .weightInKg(2).seatNumbers(4).hasElectricity(true).hasWifi(false).build();

        var carrReq1 = new CoupeCarriageRequirement(List.of(coupe1, coupe1, coupe1, coupe1, coupe2, coupe2), 50000);
        var carrReq2 = new SeatedCarriageRequirement(48, 4, 10000);
        var carrReq3 = new DiningCarriageRequirement(32, 45000, List.of(Food.BORSCH, Food.PASTA), true, false);
        var carrReq4 = new EconomyCarriageRequirement(54, 48000, true);

        return List.of(carrReq1, carrReq2, carrReq3, carrReq4);
    }

    private List<CarriageRequirement> seatedComposition() {
        var seatedReq1 = new SeatedCarriageRequirement(48, 4, 10000);
        var seatedReq2 = new SeatedCarriageRequirement(48, 4, 10000);
        var seatedReq3 = new DiningCarriageRequirement(32, 45000, List.of(Food.BORSCH), true, false);

        return List.of(seatedReq1, seatedReq2, seatedReq3);
    }

    private List<CarriageRequirement> economyComposition() {
        var economyReq1 = new EconomyCarriageRequirement(54, 48000, false);
        var economyReq2 = new EconomyCarriageRequirement(54, 48000, true);

        return List.of(economyReq1, economyReq2);
    }
}