package com.sveta.carriage.passenger;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.models.Seat;

import java.util.List;

public abstract class PassengerCarriage extends Carriage {
    public static final int AV_PEOPLE_WEIGHT = 62;

    public abstract double getPriceInByn();
    public abstract int getNumberOfPlaces();
    public abstract List<Seat> getAllSeats();

    public int getNumberOfEmptyPlaces() {
        return getNumberOfPlaces() - getAllSeats().size();
    }
}
