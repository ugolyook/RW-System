package com.sveta.carriage.passenger;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.ElectricCarriage;
import com.sveta.carriage.passenger.models.Food;
import com.sveta.carriage.passenger.models.Seat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiningCarriage extends PassengerCarriage implements ElectricCarriage {
    private int seatsLimit;
    private int kitchenWeightKg;
    private int seatWeightKg;
    private int baseCarriageWeightKg;

    public List<Seat> seats = new ArrayList<>(seatsLimit);
    public Food food;
    boolean hasHotKitchen;
    boolean deliveryToTheRoom;

    public DiningCarriage(
            int seatsLimit,
            int kitchenWeightKg,
            int seatWeightKg,
            int baseCarriageWeightKg,
            boolean hasHotKitchen,
            boolean deliveryToTheRoom) {
        this.seatsLimit = seatsLimit;
        this.kitchenWeightKg = kitchenWeightKg;
        this.seatWeightKg = seatWeightKg;
        this.baseCarriageWeightKg = baseCarriageWeightKg;
        this.hasHotKitchen = hasHotKitchen;
        this.deliveryToTheRoom = deliveryToTheRoom;
        this.seats = new ArrayList<>(seatsLimit);
    }

    public DiningCarriage(
            int seatsLimit,
            boolean hasHotKitchen,
            boolean deliveryToTheRoom) {
        this(
                seatsLimit, 10000, 15,
                48000, hasHotKitchen, deliveryToTheRoom
        );
    }

    public DiningCarriage(
            boolean hasHotKitchen,
            boolean deliveryToTheRoom) {
        this(
                32, 10000, 15,
                48000, hasHotKitchen, deliveryToTheRoom
        );
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
        int weight = (seatsLimit * seatWeightKg) +
                (getNumberOfPlaces() * AV_PEOPLE_WEIGHT) + baseCarriageWeightKg;

        if (hasHotKitchen) {
            int kitchenWeight = kitchenWeightKg - weight;
            weight = weight + kitchenWeight;
        }
        return weight;
    }

    public int getSeatsLimit() { return seatsLimit; }
    public void setSeatsLimit(int seatsLimit) { this.seatsLimit = seatsLimit; }

    public int getKitchenWeightKg() { return kitchenWeightKg; }
    public void setKitchenWeightKg(int kitchenWeightKg) { this.kitchenWeightKg = kitchenWeightKg; }

    public int getSeatWeightKg() { return seatWeightKg; }
    public void setSeatWeightKg(int seatWeightKg) { this.seatWeightKg = seatWeightKg; }

    public int getBaseCarriageWeightKg() { return baseCarriageWeightKg; }
    public void setBaseCarriageWeightKg(int baseCarriageWeightKg) { this.baseCarriageWeightKg = baseCarriageWeightKg; }

    public boolean isHasHotKitchen() { return hasHotKitchen; }
    public void setHasHotKitchen(boolean hasHotKitchen) { this.hasHotKitchen = hasHotKitchen; }

    public boolean isDeliveryToTheRoom() { return deliveryToTheRoom; }
    public void setDeliveryToTheRoom(boolean deliveryToTheRoom) { this.deliveryToTheRoom = deliveryToTheRoom; }

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
