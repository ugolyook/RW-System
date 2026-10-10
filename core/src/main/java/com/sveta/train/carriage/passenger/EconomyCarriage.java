package com.sveta.train.carriage.passenger;

import com.sveta.train.carriage.ElectricCarriage;
import com.sveta.train.carriage.passenger.models.Seat;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(callSuper = true)
public class EconomyCarriage extends PassengerCarriage implements ElectricCarriage {
    private final int seatsLimit;
    private final List<Seat> seats;
    boolean hasBioToilets;

    @Builder
    public EconomyCarriage(
            int seatsLimit,
            int baseCarriageWeightKg,
            boolean hasBioToilets
    ) {
        super(baseCarriageWeightKg);
        this.seatsLimit = seatsLimit;
        this.hasBioToilets = hasBioToilets;
        this.seats = new ArrayList<>(seatsLimit);
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
}