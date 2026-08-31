package com.sveta.carriage.passenger;

import com.sveta.carriage.passenger.models.Seat;

import java.util.ArrayList;
import java.util.List;

public class EconomyCarriage extends PassengerCarriage {
    private int SEATS_LIMIT = 54;
    public List<Seat> seats = new ArrayList<>(SEATS_LIMIT);

    boolean hasBioToilets;

    public EconomyCarriage(int SEATS_LIMIT, boolean hasBioToilets) {
        this.SEATS_LIMIT = SEATS_LIMIT;
        this.hasBioToilets = hasBioToilets;
    }

    public EconomyCarriage(boolean hasBioToilets) {
        this.hasBioToilets = hasBioToilets;
    }

    @Override
    public double getPrice() {
        return 3.0;
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
        int SEATS_KG = 15;
        int TOILET_KG = 150;
        int NUMBER_OF_TOILETS = 2;

        int weight = (SEATS_LIMIT * SEATS_KG) + (getNumberOfPlaces() * avPeopleWeight);
        if (hasBioToilets) {
            weight = weight + (NUMBER_OF_TOILETS * TOILET_KG);
        }

        return weight;
    }
}
