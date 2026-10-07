package com.sveta.train.carriage.passenger;

import com.sveta.train.carriage.ElectricCarriage;
import com.sveta.train.carriage.passenger.models.Food;
import com.sveta.train.carriage.passenger.models.Seat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class DiningCarriage extends PassengerCarriage implements ElectricCarriage {
    private final int seatsLimit;
    private final List<Food> food;
    private final boolean hasHotKitchen;
    private final boolean deliveryToTheRoom;

    public DiningCarriage(
            int seatsLimit,
            int baseCarriageWeightKg,
            boolean hasHotKitchen,
            boolean deliveryToTheRoom,
            List<Food> food
    ) {
        super(baseCarriageWeightKg);
        this.seatsLimit = seatsLimit;
        this.hasHotKitchen = hasHotKitchen;
        this.deliveryToTheRoom = deliveryToTheRoom;
        this.food = food != null ? List.copyOf(food) : List.of();
    }

    @Override
    public int getPassengerCapacity() {
        return seatsLimit;
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