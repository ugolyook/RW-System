package com.sveta.carriage.passenger;

import com.sveta.carriage.ElectricCarriage;
import com.sveta.carriage.passenger.models.Seat;

import java.util.ArrayList;
import java.util.List;

public class EconomyCarriage extends PassengerCarriage implements ElectricCarriage {
    private int seatsLimit;
    private int seatWeightKg;
    private int toiletWeightKg;
    private int numberOfToilets;
    private int baseCarriageWeightKg;

    public List<Seat> seats;
    boolean hasBioToilets;

    public EconomyCarriage(
            int seatsLimit,
            int seatWeightKg,
            int toiletWeightKg,
            int numberOfToilets,
            int baseCarriageWeightKg,
            boolean hasBioToilets
    ) {
        this.seatsLimit = seatsLimit;
        this.seatWeightKg = seatWeightKg;
        this.toiletWeightKg = toiletWeightKg;
        this.numberOfToilets = numberOfToilets;
        this.baseCarriageWeightKg = baseCarriageWeightKg;
        this.hasBioToilets = hasBioToilets;
        this.seats = new ArrayList<>(seatsLimit);
    }

    public EconomyCarriage(int seatsLimit, boolean hasBioToilets) {
        this(
                seatsLimit, 12, 150,
                2, 48000, hasBioToilets
        );
    }

    public EconomyCarriage(boolean hasBioToilets) {
        this(
                54, 12, 150,
                2, 48000, hasBioToilets
        );
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
        int weight = (seatsLimit * seatWeightKg) +
                (getNumberOfPlaces() * AV_PEOPLE_WEIGHT) + baseCarriageWeightKg;

        if (hasBioToilets) {
            weight = weight + (numberOfToilets * toiletWeightKg);
        }

        return weight;
    }

    public int getSeatsLimit() {
        return seatsLimit;
    }

    public void setSeatsLimit(int seatsLimit) {
        this.seatsLimit = seatsLimit;
    }

    public int getSeatWeightKg() {
        return seatWeightKg;
    }

    public void setSeatWeightKg(int seatWeightKg) {
        this.seatWeightKg = seatWeightKg;
    }

    public int getToiletWeightKg() {
        return toiletWeightKg;
    }

    public void setToiletWeightKg(int toiletWeightKg) {
        this.toiletWeightKg = toiletWeightKg;
    }

    public int getNumberOfToilets() {
        return numberOfToilets;
    }

    public void setNumberOfToilets(int numberOfToilets) {
        this.numberOfToilets = numberOfToilets;
    }

    public int getBaseCarriageWeightKg() {
        return baseCarriageWeightKg;
    }

    public void setBaseCarriageWeightKg(int baseCarriageWeightKg) {
        this.baseCarriageWeightKg = baseCarriageWeightKg;
    }

    public boolean isHasBioToilets() {
        return hasBioToilets;
    }

    public void setHasBioToilets(boolean hasBioToilets) {
        this.hasBioToilets = hasBioToilets;
    }

    @Override
    public String toString() {
        return "EconomyCarriage{" +
                "SEATS_LIMIT=" + seatsLimit +
                ", seats=" + seats +
                ", hasBioToilets=" + hasBioToilets +
                '}';
    }
}
