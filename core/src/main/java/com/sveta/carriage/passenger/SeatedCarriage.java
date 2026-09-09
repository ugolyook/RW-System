package com.sveta.carriage.passenger;

import com.sveta.carriage.ElectricCarriage;
import com.sveta.carriage.passenger.models.Seat;

import java.util.ArrayList;
import java.util.List;

public class SeatedCarriage extends PassengerCarriage implements ElectricCarriage {
    private int seatsLimit = 48;
    public List<Seat> seats = new ArrayList<>(seatsLimit);

    int bicycleSpots;
    double seatPitchSm;

    public SeatedCarriage(int SEATS_LIMIT, int bicycleSpots, double seatPitchSm) {
        this.seatsLimit = SEATS_LIMIT;
        this.bicycleSpots = bicycleSpots;
        this.seatPitchSm = seatPitchSm;
    }

    public SeatedCarriage(int bicycleSpots, double seatPitchSm) {
        this.bicycleSpots = bicycleSpots;
        this.seatPitchSm = seatPitchSm;
    }

    @Override
    public double getPriceInBYN() {
        return 11.8;
    }

    @Override
    public int getPassengerCapacity() {
        return seatsLimit;
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
        int SEATS_KG = 12;
        int BICYCLE_KG = 11;
        int CARRIAGE_KG = 40000;
        return (
                (seatsLimit * SEATS_KG) +
                        (bicycleSpots * BICYCLE_KG) +
                        (getNumberOfPlaces() * AV_PEOPLE_WEIGHT) +
                        CARRIAGE_KG
        );
    }

    @Override
    public String toString() {
        return "Seated{" +
                "SEATS_LIMIT=" + seatsLimit +
                ", seats=" + seats +
                ", bicycleSpots=" + bicycleSpots +
                ", seatPitchSm=" + seatPitchSm +
                '}';
    }
}
