package com.sveta;

import com.sveta.carriage.Carriage;
import com.sveta.factory.PassengerTrainFactory;
import com.sveta.train.Train;

import java.util.List;

public class Main {
    static void main(String[] args) {
        PassengerTrainFactory factory = new PassengerTrainFactory(18);

        try {
            System.out.println("=== TRAIN CREATION ===");

            List<Carriage> carriages = factory.getCarriageFactory()
                    .createCarriages(5, factory);

            Train train = factory.createTrain(5, carriages);

            System.out.println("\n=== TRAIN INFORMATION ===");
            System.out.println(train);

            System.out.println("\n=== ADDITIONAL INFORMATION ===");
            System.out.println("Number of carriages: " + train.getCarriageCount());
            System.out.println("Total passenger capacity: " + train.getTotalPassengerCap() + " seats");
            System.out.println("Train number: " + train.getTrainNumber());

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Runtime Error: " + e.getMessage());
        }

        System.out.println("\n=== LIMIT TEST ===");
        testLimit(factory);
    }

    private static void testLimit(PassengerTrainFactory factory) {
        try {
            System.out.println("Attempting to create a train with 20 carriages (maximum is 18)...");

            List<Carriage> carriages = factory.getCarriageFactory()
                    .createCarriages(20, factory);

            Train bigTrain = factory.createTrain(20, carriages);
            System.out.println(bigTrain);

        } catch (IllegalArgumentException e) {
            System.out.println("Expected error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Runtime Error: " + e.getMessage());
        }
    }
}