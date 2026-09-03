package com.sveta.carriage.passenger;

import com.sveta.carriage.passenger.models.Food;
import com.sveta.carriage.passenger.models.Seat;

import java.util.ArrayList;
import java.util.List;

public class DiningCarriage extends PassengerCarriage {
    private int SEATS_LIMIT = 32;
    public List<Seat> seats = new ArrayList<>(SEATS_LIMIT);

    public Food food;
    boolean hasHotKitchen;
    boolean deliveryToTheRoom;

    public DiningCarriage(
            int SEATS_LIMIT,
            boolean hasHotKitchen,
            boolean deliveryToTheRoom) {
        this.SEATS_LIMIT = SEATS_LIMIT;
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
    public double getPrice() {
        return 1;
    }

    @Override
    public int getPassengerCapacity() {
        return SEATS_LIMIT;
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
        int KITCHEN_KG = 60000;
        int SEATS_KG = 15;
        int CARRIAGE_KG = 60000;

        int weight = (SEATS_LIMIT * SEATS_KG) +
                (getNumberOfPlaces() * avPeopleWeight) + CARRIAGE_KG;

        if (hasHotKitchen) {
            int kitchenWeight = KITCHEN_KG - weight;
            weight = weight + kitchenWeight;
        }
        return weight;
    }

    @Override
    public String toString() {
        return "DiningCarriage{" +
                "SEATS_LIMIT=" + SEATS_LIMIT +
                ", seats=" + seats +
                ", food=" + food +
                ", hasHotKitchen=" + hasHotKitchen +
                ", deliveryToTheRoom=" + deliveryToTheRoom +
                '}';
    }

    public Object order(Food food, int quantity) {
        return null;
    }
}
