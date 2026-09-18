package com.sveta.train.carriage.passenger;

import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.ElectricCarriage;
import com.sveta.train.carriage.passenger.models.Seat;

import java.util.List;

public abstract class PassengerCarriage extends Carriage implements ElectricCarriage {
    public PassengerCarriage(int baseCarriageWeightInKg) {
        super(baseCarriageWeightInKg);
    }

    public abstract int getNumberOfPlaces();
    public abstract List<Seat> getAllSeats();

    public int getNumberOfEmptyPlaces() {
        return getNumberOfPlaces() - getAllSeats().size();
    }
}
