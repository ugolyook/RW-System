package com.sveta.train.carriage.passenger.models;

public class Seat {
    private final int number;
    private final SeatType type;

    public Seat(
            int number,
            SeatType type
    ) {
        this.number = number;
        this.type = type;
    }

    public SeatType getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }


    @Override
    public String toString() {
        return "Seat #" + number + " (" + type + ")";
    }
}