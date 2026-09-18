package com.sveta.train.carriage.passenger;

import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.ElectricCarriage;
import com.sveta.train.carriage.passenger.models.Food;
import com.sveta.train.carriage.passenger.models.Seat;

import java.util.List;
import java.util.Optional;

public class DiningCarriage extends PassengerCarriage implements ElectricCarriage {
    private final int seatsLimit;

    public Food food;
    boolean hasHotKitchen;
    boolean deliveryToTheRoom;

    public DiningCarriage(
            int seatsLimit,
            int baseCarriageWeightKg,
            boolean hasHotKitchen,
            boolean deliveryToTheRoom) {
        super(baseCarriageWeightKg);
        this.seatsLimit = seatsLimit;
        this.hasHotKitchen = hasHotKitchen;
        this.deliveryToTheRoom = deliveryToTheRoom;
    }

    @Override
    public int getPassengerCapacity() {
        return seatsLimit;
    }

    @Override
    public String toString() {
        return "DiningCarriage{" +
                "SEATS_LIMIT=" + seatsLimit +
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

    @Override
    public int getNumberOfPlaces() {
        return 0;
    }

    @Override
    public List<Seat> getAllSeats() {
        return List.of();
    }
}