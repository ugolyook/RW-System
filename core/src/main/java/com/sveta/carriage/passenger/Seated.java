package com.sveta.carriage.passenger;

import com.sveta.carriage.passenger.models.Seat;

import java.util.ArrayList;
import java.util.List;

public class Seated extends PassengerCarriage {
    private int SEATS_LIMIT = 48;
    public List<Seat> seats = new ArrayList<>(SEATS_LIMIT);

    int bicycleSpots;
    double seatPitchSm;

    public Seated(int SEATS_LIMIT, int bicycleSpots, double seatPitchSm) {
        this.SEATS_LIMIT = SEATS_LIMIT;
        this.bicycleSpots = bicycleSpots;
        this.seatPitchSm = seatPitchSm;
    }

    public Seated(int bicycleSpots, double seatPitchSm) {
        this.bicycleSpots = bicycleSpots;
        this.seatPitchSm = seatPitchSm;
    }

    @Override
    public double getPrice() {
        return 11.8;
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
        int BICYCLE_KG = 11;
        return (
                (SEATS_LIMIT * SEATS_KG) +
                        (bicycleSpots * BICYCLE_KG) +
                        (getNumberOfPlaces() * avPeopleWeight)
        );
    }
}
