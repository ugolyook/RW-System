package com.sveta.train.carriage.passenger;

import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.ElectricCarriage;
import com.sveta.train.carriage.passenger.models.Seat;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public abstract class PassengerCarriage extends Carriage implements ElectricCarriage {
    public PassengerCarriage(int baseCarriageWeightInKg) {
        super(baseCarriageWeightInKg);
    }

    public abstract int getNumberOfPlaces();
    public abstract List<Seat> getAllSeats();
}