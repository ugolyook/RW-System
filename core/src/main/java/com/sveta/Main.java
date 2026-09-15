package com.sveta;

import com.sveta.carriage.Carriage;
import com.sveta.factory.locomotive.LocomotiveFactory;
import com.sveta.train.formatter.BaseTrainInfoFormatter;
import com.sveta.train.formatter.TrainInfoFormatter;
import com.sveta.factory.train.PassengerTrainFactory;
import com.sveta.factory.carriage.BaseCarriageFactory;
import com.sveta.factory.carriage.CarriageRequirement;
import com.sveta.factory.carriage.coupe.CoupeCarriageFactory;
import com.sveta.factory.carriage.coupe.CoupeCarriageRequirement;
import com.sveta.factory.carriage.coupe.CoupeRequirement;
import com.sveta.factory.carriage.seated.SeatedCarriageFactory;
import com.sveta.factory.carriage.seated.SeatedCarriageRequirement;

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

        CarriageRequirement seatedCarriageReq = new SeatedCarriageRequirement(
                48,
                10000,
                true,
                true,
                4,
                2
        );

        var allRequirement = List.of(coupeCarriageReq1, coupeCarriageReq2, seatedCarriageReq);

        return baseCarriageFactory.createAll(allRequirement);
    }

    private BaseCarriageFactory configureBaseCarriageFactory(){
        var coupeFactory = new CoupeCarriageFactory();
        var seatedFactory  = new SeatedCarriageFactory();

        var allCarriageFactories = List.of(coupeFactory,seatedFactory);

        return new BaseCarriageFactory(allCarriageFactories);
    }

    private PassengerTrainFactory configureTrainFactory(){
        var locomotiveFactory = new LocomotiveFactory();
        return new PassengerTrainFactory(DEFAULT_CARRIAGE_LIMIT_PER_TRAIN, locomotiveFactory);
    }
}