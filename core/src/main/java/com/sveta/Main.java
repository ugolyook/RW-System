package com.sveta;

import com.sveta.factory.PassengerTrainFactory;
import com.sveta.train.Train;

public class Main {
    public static void main(String[] args) {
        PassengerTrainFactory factory = new PassengerTrainFactory(18);
        try {
            System.out.println("=== TRAIN CREATION ===");
            Train train = factory.createTrain(5);

            System.out.println("\n=== TRAIN INFORMATION ===");
            System.out.println(train);

            System.out.println("\n=== ADDITIONAL INFORMATION ===");
            System.out.println("Number of carriages: " + train.getCarriageCount());
            System.out.println("Total passenger capacity: " + train.getTotalPassengerCap() + " seats");

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== LIMIT TEST ===");
        testLimit(factory);
    }

    private static void testLimit(PassengerTrainFactory factory) {
        try {
            System.out.println("Attempting to create a train with 20 carriages (maximum is 18)...");
            Train bigTrain = factory.createTrain(20);
            System.out.println(bigTrain);
        } catch (IllegalArgumentException e) {
            System.out.println("Expected error: " + e.getMessage());
        }
    }
}

