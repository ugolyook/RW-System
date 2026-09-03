package com.sveta;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.*;
import com.sveta.train.PassengerTrain;
import com.sveta.train.Train;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class TrainFactory {
    int sizeLimit;
    int lengthLimit = 18;
    int maxDiningCar = 1;
    final AtomicLong counter = new AtomicLong((int) System.currentTimeMillis());

    public TrainFactory(int sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    public void setLengthLimit(int lengthLimit) {
        this.lengthLimit = lengthLimit;
    }

    public void setMaxDiningCar(int maxDiningCar) {
        this.maxDiningCar = maxDiningCar;
    }

    public Train createTrain(int numberOfCarr) {
        Train train = new PassengerTrain(generateTrainNumber());
        List<Carriage> carriages = generateCarriages(numberOfCarr);

        if (!isValid(carriages)) {
            throw new IllegalStateException("Train configuration exceeds limits!");
        }

        carriages.forEach(train::addCarriage);
        return train;
    }

    public int generateTrainNumber() {
        return nextUnique();
    }

    public List<Carriage> generateCarriages(int numberOfCarr) {
        if (numberOfCarr >= lengthLimit) {
            throw new IllegalArgumentException("Number of carriages " +
                    "(" + numberOfCarr + ") exceeds size limit (" + sizeLimit + ")"
            );
        }

        Scanner scanner = new Scanner(System.in);
        List<Carriage> carriages = new ArrayList<>();

        createTypeOfCarriages(numberOfCarr, scanner, carriages);
        createDinerCarriage(scanner, carriages);

        scanner.close();
        return carriages;
    }

    private void createDinerCarriage(Scanner scanner, List<Carriage> carriages) {
        long currentDiningCount = carriages.stream()
                .filter(c -> c instanceof DiningCarriage)
                .count();

        if (currentDiningCount >= maxDiningCar) {
            System.out.println("Max dining carriages already reached (" + maxDiningCar + ")");
            return;
        }

        System.out.println("\nWould u like to have dining carriages ?");
        String input = scanner.nextLine().trim().toLowerCase();

        if (input.startsWith("y")) {
            Carriage carriage = new DiningCarriage(true, true);
            carriages.add(carriage);
        }
    }


    private void createTypeOfCarriages(int numberOfCarr, Scanner scanner, List<Carriage> carriages) {
        Carriage carriage;
        for (int i = 0; i < numberOfCarr; i++) {
            System.out.println("What type of carriage would u prefer: ");
            System.out.println("Coupe \nEconomy \nSeated:");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.startsWith("c")) {
                carriage = new CoupeCarriage(
                        false,
                        true);
            } else if (input.startsWith("e")) {
                carriage = new EconomyCarriage(true);
            } else {
                carriage = new SeatedCarriage(2, 2.0);
            }
            carriages.add(carriage);
        }
    }

    private boolean isValid(List<Carriage> carriages) {
        if (carriages.size() > sizeLimit) {
            System.out.println("Size limit exceeded: " + carriages.size() + " > " + sizeLimit);
            return false;
        }

        long diningCount = carriages.stream()
                .filter(c -> c instanceof DiningCarriage)
                .count();
        if (diningCount > maxDiningCar) {
            System.out.println("Too many dining cars: " + diningCount + " > " + maxDiningCar);
            return false;
        }

        if (carriages.size() >lengthLimit) {
            System.out.println("Too many carriages: " + carriages.size() +
                    " > " + lengthLimit);
            return false;
        }

        return true;
    }

    public int nextUnique() {
        return Math.toIntExact(counter.incrementAndGet());
    }
}

