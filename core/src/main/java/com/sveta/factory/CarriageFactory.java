package com.sveta.factory;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.CoupeCarriage;
import com.sveta.carriage.passenger.DiningCarriage;
import com.sveta.carriage.passenger.EconomyCarriage;
import com.sveta.carriage.passenger.SeatedCarriage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class CarriageFactory {
    final Logger LOGGER = Logger.getLogger(CarriageFactory.class.getName());
    Scanner scanner;

    public void createDinerCarriage( List<Carriage> carriages, int maxDiningCar) {
        long currentDiningCount = carriages.stream()
                .filter(c -> c instanceof DiningCarriage)
                .count();

        if (currentDiningCount >= maxDiningCar) {
            LOGGER.warning("Max dining carriages already reached (" + maxDiningCar + ")");
            return;
        }

        System.out.println("\nWould u like to have dining carriages ?");
        String input = scanner.nextLine().trim().toLowerCase();

        if (input.equals("yes")) {
            Carriage carriage = new DiningCarriage(true, true);
            carriages.add(carriage);
        }
    }

    public List<Carriage> generateCarriages(int numberOfCarr, TrainFactory factory) {
        if (numberOfCarr > factory.lengthLimit) {
            throw new IllegalArgumentException("Number of carriages " +
                    "(" + numberOfCarr + ") exceeds size limit (" + factory.sizeLimit + ")"
            );
        }

        List<Carriage> carriages = createTypeOfCarriages(numberOfCarr   );
        createDinerCarriage( carriages, factory.maxDiningCar);

        return carriages;
    }

    private List<Carriage> createTypeOfCarriages(int numberOfCarr) {
        List<Carriage> carriages = new ArrayList<>();

        for (int i = 0; i < numberOfCarr; i++) {
            System.out.println("""
                    What type of carriage would u prefer:
                    Coupe
                    Economy
                    Seated""");

            String input = scanner.nextLine().trim().toLowerCase();

            Carriage carriage = switch (input) {
                case "coupe" -> new CoupeCarriage(
                        false,
                        true);
                case "economy" -> new EconomyCarriage(true);
                case "seated" -> new SeatedCarriage(2, 2.0);
                default -> new EconomyCarriage(false);
            };
            carriages.add(carriage);
        }
        return carriages;
    }
}
