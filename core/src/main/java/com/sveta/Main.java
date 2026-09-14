package com.sveta;

import com.sveta.carriage.Carriage;
import com.sveta.dto.CarriageRequirements;
import com.sveta.factory.PassengerTrainFactory;
import com.sveta.factory.carriageFactory.CarriageFactory;
import com.sveta.train.Train;

import java.util.List;

public class Main {
    static void main(String[] args) {
        PassengerTrainFactory factory = new PassengerTrainFactory(18);

        CarriageRequirements requirements = new CarriageRequirements.Builder()
                .withCoupe(3, true, false)
                .withEconomy(5, true)
                .withSeated(2, 6, 85.0)
                .withDining(true, true)
                .build();

        CarriageFactory carriageFactory = new CarriageFactory(factory);
        List<Carriage> trainCarriages = carriageFactory.createCarriages(requirements);

        System.out.println("=== TRAIN CREATION ===");

        Train train = factory.createTrain(trainCarriages);

        System.out.println("\n=== TRAIN INFORMATION ===");
        System.out.println(train);

        System.out.println("\n=== ADDITIONAL INFORMATION ===");
        System.out.println("Number of carriages: " + train.getCarriageCount());
        System.out.println("Total passenger capacity: " + train.getTotalPassengerCap() + " seats");
        System.out.println("Train number: " + train.getTrainNumber());

        System.out.println("\n=== LIMIT TEST ===");
    }
}