package com.sveta.carriage.passenger;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.ElectricCarriage;
import com.sveta.carriage.passenger.models.Food;
import com.sveta.carriage.passenger.models.Seat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiningCarriage extends PassengerCarriage implements ElectricCarriage {
    private int seatsLimit = 32;
    public List<Seat> seats = new ArrayList<>(seatsLimit);

    public Food food;
    boolean hasHotKitchen;
    boolean deliveryToTheRoom;

    public DiningCarriage(
            int SEATS_LIMIT,
            boolean hasHotKitchen,
            boolean deliveryToTheRoom) {
        this.seatsLimit = SEATS_LIMIT;
        this.hasHotKitchen = hasHotKitchen;
        this.deliveryToTheRoom = deliveryToTheRoom;
    }

    public DiningCarriage(
            boolean hasHotKitchen,
            boolean deliveryToTheRoom) {
        this.hasHotKitchen = hasHotKitchen;
        this.deliveryToTheRoom = deliveryToTheRoom;
    }

    @Override
    public double getPriceInBY() {
        return 1;
    }

    @Override
    public int getPassengerCapacity() {
        return seatsLimit;
    }

    @Override
    public int getNumberOfPlaces() {
        return seats.size();
    }

    @Override
    public List<Seat> getAllSeats() {
        return seats;
    }

    @Override
    public int getKgWeight() {
        int KITCHEN_KG = 10000;
        int SEATS_KG = 15;
        int CARRIAGE_KG = 48000;

        int weight = (seatsLimit * SEATS_KG) +
                (getNumberOfPlaces() * AV_PEOPLE_WEIGHT) + CARRIAGE_KG;

        if (hasHotKitchen) {
            int kitchenWeight = KITCHEN_KG - weight;
            weight = weight + kitchenWeight;
        }
        return weight;
    }

    @Override
    public String toString() {
        return "DiningCarriage{" +
                "SEATS_LIMIT=" + seatsLimit +
                ", seats=" + seats +
                ", food=" + food +
                ", hasHotKitchen=" + hasHotKitchen +
                ", deliveryToTheRoom=" + deliveryToTheRoom +
                '}';
    }

    public Optional<DiningCarriage> findRestaurant(List<Carriage> carriages) {
        return carriages.stream()
                .filter(c -> c instanceof DiningCarriage)
                .map(DiningCarriage.class::cast)
                .findFirst();
    }

    public double orderInRestaurant(Food item, int quantity, List<Carriage> carriages) {
        return findRestaurant(carriages)
                .map(r -> r.order(item, quantity))
                .orElseThrow(() -> new IllegalStateException("There are no dining carriage"));
    }

    public double order(Food food, int quantity) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }
}
