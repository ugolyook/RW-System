package com.sveta.carriage.passenger;

import com.sveta.carriage.ElectricCarriage;
import com.sveta.carriage.passenger.models.Seat;

import java.util.ArrayList;
import java.util.List;

public class EconomyCarriage extends PassengerCarriage implements ElectricCarriage {
    private int seatsLimit = 54;
    public List<Seat> seats = new ArrayList<>(seatsLimit);

    boolean hasBioToilets;

    public EconomyCarriage(int SEATS_LIMIT, boolean hasBioToilets) {
        this.seatsLimit = SEATS_LIMIT;
        this.hasBioToilets = hasBioToilets;
    }

    public EconomyCarriage(boolean hasBioToilets) {
        this.hasBioToilets = hasBioToilets;
    }

    @Override
    public double getPriceInBY() {
        return 3.0;
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
        int SEATS_KG = 12;
        int TOILET_KG = 150;
        int NUMBER_OF_TOILETS = 2;
        int CARRIAGE_KG = 48000;

        int weight = (seatsLimit * SEATS_KG) +
                (getNumberOfPlaces() * AV_PEOPLE_WEIGHT) + CARRIAGE_KG;

        if (hasBioToilets) {
            weight = weight + (NUMBER_OF_TOILETS * TOILET_KG);
        }

        return weight;
    }

    @Override
    public String toString() {
        return "EconomyCarriage{" +
                "SEATS_LIMIT=" + seatsLimit +
                ", seats=" + seats +
                ", hasBioToilets=" + hasBioToilets +
                '}';
    }
}
