package com.sveta.carriage.passenger;

import com.sveta.carriage.passenger.models.Seat;

import java.util.List;

public class DiningCarriage extends PassengerCarriage {
    int seatingCapacity;
    boolean hasHotKitchen;

    @Override
    public int getNumberOfPlaces() {
        return 0;
    }

    @Override
    public List<Seat> getAllSeats() {
        return List.of();
    }

    @Override
    public int getKgWeight() {
        return 0;
    }
}
