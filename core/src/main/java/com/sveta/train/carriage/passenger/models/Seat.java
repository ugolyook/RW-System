package com.sveta.train.carriage.passenger.models;

public class Seat {
    int number;
    SeatType type;
    private boolean isOccupied;

    public Seat(
            int number,
            SeatType type,
            boolean isOccupied
    ) {
        this.number = number;
        this.type = type;
        this.isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    @Override
    public String toString() {
        return "Seat #" + number + " (" + type + (isOccupied ? ", occupied" : ", available") + ")";
    }
}
