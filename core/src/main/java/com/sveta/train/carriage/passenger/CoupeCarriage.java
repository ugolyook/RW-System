package com.sveta.train.carriage.passenger;

import com.sveta.train.carriage.ElectricCarriage;
import com.sveta.train.carriage.passenger.models.Seat;

import java.util.ArrayList;
import java.util.List;

public class CoupeCarriage extends PassengerCarriage implements ElectricCarriage {
    private final int coupeLimit;
    private final List<Coupe> coupes;

    public CoupeCarriage(
            List<Coupe> coupes,
            int baseCarriageWeightKg
    ) {
        super(baseCarriageWeightKg);
        this.coupeLimit = coupes.size();
        this.coupes = new ArrayList<>(coupes);
    }

    @Override
    public int getPassengerCapacity() {
        return coupeLimit * Coupe.SEATS_PER_COUPE;
    }

    @Override
    public int getNumberOfPlaces() {
        return getAllSeats().size();
    }

    @Override
    public List<Seat> getAllSeats() {
        List<Seat> all = new ArrayList<>();
        for (Coupe coupe : coupes) {
            all.addAll(coupe.getAllSeats());
        }
        return all;
    }

    public static class Coupe {
        public static final int SEATS_PER_COUPE = 4;
        public List<Seat> seats;

        public Coupe() {
            this.seats = new ArrayList<>(SEATS_PER_COUPE);
        }

        public List<Seat> getAllSeats() {
            return seats;
        }
    }

    @Override
    public String toString() {
        return "CoupeCarriage{" +
                "coupeLimit=" + coupeLimit +
                ", coupes=" + coupes +
                '}';
    }
}