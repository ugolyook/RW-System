package com.sveta.carriage.passenger;

import com.sveta.carriage.passenger.models.Seat;

import java.util.ArrayList;
import java.util.List;

public class EconomyCarriage extends PassengerCarriage {
    private final static int SEATS_LIMIT = 54;
    public List<Seat> seats = new ArrayList<>(SEATS_LIMIT);

    boolean hasBioToilet;

//    public Economy(int seats);
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
