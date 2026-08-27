package com.sveta.carriage.passenger;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.passenger.models.Seat;

import java.util.List;

public abstract class PassengerCarriage extends Carriage {
    public abstract int getNumberOfPlaces();
    public abstract List<Seat> getAllSeats();

    public int getNumberOfEmptyPlaces(){
        return getNumberOfPlaces() - getAllSeats().size();
    }
}
