package com.sveta.train.carriage.passenger;

import com.sveta.train.carriage.ElectricCarriage;
import com.sveta.train.carriage.passenger.models.Seat;

import java.util.ArrayList;
import java.util.List;

public class SeatedCarriage extends PassengerCarriage implements ElectricCarriage {
    private int seatsLimit;
    private final boolean bicycleSpots;

    public List<Seat> seats = new ArrayList<>(seatsLimit);

    public SeatedCarriage(
            int seatsLimit,
            boolean bicycleSpots,
            int baseCarriageWeightKg
    ) {
        super(baseCarriageWeightKg);
        this.seatsLimit = seatsLimit;
        this.bicycleSpots = bicycleSpots;
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
    public String toString() {
        return "Seated{" +
                "SEATS_LIMIT=" + seatsLimit +
                ", seats=" + seats +
                ", bicycleSpots=" + bicycleSpots +
                '}';
    }
}
