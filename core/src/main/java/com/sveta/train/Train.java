package com.sveta.train;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.DiningCarriage;
import com.sveta.carriage.passenger.models.Food;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public abstract class Train {
    private Locomotive locomotive;
    private final List<Carriage> carriages = new ArrayList<>();
    private final int trainNumber;

    public Train(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public void addCarriage(Carriage carriage) {
        carriages.add(carriage);
    }

    public void removeCarriage(int id) {
        carriages.remove(id);
    }

    public Iterator<Carriage> getAllCarriage() {
        return carriages.iterator();
    }

    public void insertCarriage(int id, Carriage carriage) {
        carriages.add(id, carriage);
    }

    public int getTotalPassengerCap() {
        return carriages.stream()
                .mapToInt(Carriage::getPassengerCapacity)
                .sum();
    }

    public double getTotalWeight() {
        return carriages.stream()
                .mapToInt(Carriage::getKgWeight)
                .sum();
    }

    public int getCarriageCount() {
        return carriages.size();
    }

    public void printTrainInfo() {
        System.out.println("Train №" + trainNumber);
        System.out.println("Carriages: " + getCarriageCount());
        System.out.println("Passenger seats: " + getTotalPassengerCap());
        System.out.println("Total weight: " + getTotalWeight() + " т");
        System.out.println("--- List of railcars ---");
        carriages.forEach(System.out::println);
    }

    public Optional<DiningCarriage> findRestaurant() {
        return carriages.stream()
                .filter(c -> c instanceof DiningCarriage)
                .map(c -> (DiningCarriage) c)
                .findFirst();
    }

    public double orderInRestaurant(Food item, int quantity) {
        return (double) findRestaurant()
                .map(r -> r.order(item, quantity))
                .orElseThrow(() -> new IllegalStateException("There are no dining carriage"));
    }
}