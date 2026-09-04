package com.sveta;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.*;
import com.sveta.dto.CarriageInfoDTO;
import com.sveta.train.Locomotive;
import com.sveta.train.PassengerTrain;
import com.sveta.train.Train;
import com.sveta.validator.TrainValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class TrainFactory {
    int sizeLimit;
    int lengthLimit = 18;
    int maxDiningCar = 1;
    final AtomicLong trainNumber = new AtomicLong((int) System.currentTimeMillis());

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
        Locomotive locomotive = new Locomotive(18, 570, 85000);

        Train train = new PassengerTrain(generateTrainNumber());
        train.setLocomotive(locomotive);

        List<Carriage> carriages = generateCarriages(numberOfCarr);

        TrainValidator trainValidator = new TrainValidator();
        CarriageInfoDTO dto = new CarriageInfoDTO(carriages, sizeLimit, maxDiningCar, lengthLimit);

        if (!trainValidator.isResultTrainValid(dto)) {
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

        List<Carriage> carriages;
        try (Scanner scanner = new Scanner(System.in)) {
            DiningCarriage diningCarriage =
                    new DiningCarriage(true, true);

            carriages = createTypeOfCarriages(numberOfCarr, scanner);
            diningCarriage.createDinerCarriage(scanner, carriages, maxDiningCar);
        }

        return carriages;
    }

    private List<Carriage> createTypeOfCarriages(int numberOfCarr, Scanner scanner) {
        List<Carriage> carriages = new ArrayList<>();
        Carriage carriage = null;

        for (int i = 0; i < numberOfCarr; i++) {
            System.out.println("""
                    What type of carriage would u prefer:
                    Coupe
                    Economy
                    Seated""");

            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "coupe": {
                    carriage = new CoupeCarriage(
                            false,
                            true);
                    break;
                }
                case "economy":
                    carriage = new EconomyCarriage(true);
                    break;
                case "seated":
                    carriage = new SeatedCarriage(2, 2.0);
                    break;
                default:
                    carriage = new EconomyCarriage(false);
            }
            carriages.add(carriage);
        }
        return carriages;
    }

    public int nextUnique() {
        return Math.toIntExact(trainNumber.incrementAndGet());
    }
}

