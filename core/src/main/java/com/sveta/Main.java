package com.sveta;

import com.sveta.carriage.Carriage;
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
    }

    private List<Carriage> createCarriages() {
        var coupeReq1 = new CoupeRequirement(111, 62, false, false,false, true);
        var coupeReq2 = new CoupeRequirement(11, 62, true,  false,false, false);
        var coupeReq3 = new CoupeRequirement(11, 62, false, false,true, true);

        CarriageRequirement coupeCarriageReq1 = new CoupeCarriageRequirement(
                List.of(coupeReq1, coupeReq1, coupeReq1, coupeReq1, coupeReq2, coupeReq2, coupeReq2, coupeReq3),
                true,
                true,
                50000
        );

        CarriageRequirement coupeCarriageReq2 = new CoupeCarriageRequirement(
                List.of(coupeReq1, coupeReq1, coupeReq2, coupeReq3, coupeReq3, coupeReq3, coupeReq3),
                true,
                true,
                50000
        );

        var allRequirement = getCarriageRequirements(coupeCarriageReq1, coupeCarriageReq2);

        return baseCarriageFactory.createAll(allRequirement);
    }

    private static List<CarriageRequirement> getCarriageRequirements(CarriageRequirement coupeCarriageReq1, CarriageRequirement coupeCarriageReq2) {
        CarriageRequirement seatedCarriageReq = new SeatedCarriageRequirement(
                48,
                10000,
                true,
                true,
                4,
                2
        );

        CarriageRequirement diningCarriageReq = new DiningCarriageRequirement(
                32,
                10000,
                15,
                48000,
                List.of(),
                null,
                true,
                false
        );

        CarriageRequirement economyCarriageReq = new EconomyCarriageRequirement(
                54,
                12,
                150,
                2,
                48000,
                List.of(),
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

    private BaseCarriageFactory configureBaseCarriageFactory(){
        var coupeFactory = new CoupeCarriageFactory();
        var seatedFactory  = new SeatedCarriageFactory();
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

    private PassengerTrainFactory configureTrainFactory(){
        var locomotiveFactory = new LocomotiveFactory();
        return new PassengerTrainFactory(DEFAULT_CARRIAGE_LIMIT_PER_TRAIN, locomotiveFactory);
    }
}