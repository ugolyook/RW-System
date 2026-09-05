package com.sveta.factory;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.CoupeCarriage;
import com.sveta.carriage.passenger.DiningCarriage;
import com.sveta.carriage.passenger.EconomyCarriage;
import com.sveta.carriage.passenger.SeatedCarriage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarriageFactory {
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
