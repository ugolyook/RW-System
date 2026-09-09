package com.sveta.carriage.passenger;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.ElectricCarriage;
import com.sveta.carriage.passenger.models.Seat;

import java.util.List;

public abstract class PassengerCarriage extends Carriage implements ElectricCarriage {
    public static final int AV_PEOPLE_WEIGHT = 62;

    public abstract double getPriceInBYN();
    public abstract int getNumberOfPlaces();
    public abstract List<Seat> getAllSeats();

    public int getNumberOfEmptyPlaces() {
        return getNumberOfPlaces() - getAllSeats().size();
    }
}
