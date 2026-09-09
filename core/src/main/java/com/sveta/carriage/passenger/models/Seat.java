package com.sveta.carriage.passenger.models;

public class Seat {
    int number;
    SeatType type;

    public Seat(
            int number,
            SeatType type,
            Gender genderSpecificCompartments) {
        this.number = number;
        this.type = type;
    }
}
