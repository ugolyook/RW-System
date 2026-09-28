package com.sveta.train.carriage.passenger;

import com.sveta.train.carriage.ElectricCarriage;
import com.sveta.train.carriage.passenger.models.Seat;

import java.util.ArrayList;
import java.util.List;

import static com.sveta.train.carriage.passenger.models.SeatType.BICYCLE;

public class SeatedCarriage extends PassengerCarriage implements ElectricCarriage {
    private int seatsLimit;
    public List<Seat> seats = new ArrayList<>(seatsLimit);

    public SeatedCarriage(
            int seatsLimit,
            int baseCarriageWeightKg
    ) {
        super(baseCarriageWeightKg);
        this.seatsLimit = seatsLimit;
    }

    public boolean isBicycleSpots() {
        return seats.stream().anyMatch(seat -> seat.getType() == BICYCLE);
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
                '}';
    }
}
