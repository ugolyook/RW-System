package com.sveta.carriage.passenger;

import com.sveta.carriage.passenger.models.Seat;

import java.util.ArrayList;
import java.util.List;

public class SeatedCarriage extends PassengerCarriage {
    private int SEATS_LIMIT = 48;
    public List<Seat> seats = new ArrayList<>(SEATS_LIMIT);

    int bicycleSpots;
    double seatPitchSm;

    public SeatedCarriage(int SEATS_LIMIT, int bicycleSpots, double seatPitchSm) {
        this.SEATS_LIMIT = SEATS_LIMIT;
        this.bicycleSpots = bicycleSpots;
        this.seatPitchSm = seatPitchSm;
    }

    public SeatedCarriage(int bicycleSpots, double seatPitchSm) {
        this.bicycleSpots = bicycleSpots;
        this.seatPitchSm = seatPitchSm;
    }

    @Override
    public double getPrice() {
        return 11.8;
    }

    @Override
    public int getPassengerCapacity() {
        return SEATS_LIMIT;
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
        int CARRIAGE_KG = 45000;
        return (
                (SEATS_LIMIT * SEATS_KG) +
                        (bicycleSpots * BICYCLE_KG) +
                        (getNumberOfPlaces() * avPeopleWeight) +
                        CARRIAGE_KG
        );
    }

    @Override
    public String toString() {
        return "Seated{" +
                "SEATS_LIMIT=" + SEATS_LIMIT +
                ", seats=" + seats +
                ", bicycleSpots=" + bicycleSpots +
                ", seatPitchSm=" + seatPitchSm +
                '}';
    }
}
