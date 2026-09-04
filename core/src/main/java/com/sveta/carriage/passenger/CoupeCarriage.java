package com.sveta.carriage.passenger;

import com.sveta.carriage.passenger.models.Seat;

import java.util.ArrayList;
import java.util.List;

public class CoupeCarriage extends PassengerCarriage {
    private int DEFAULT_COUPE_LIMIT = 9;
    private final List<Coupe> coupes = new ArrayList<>(DEFAULT_COUPE_LIMIT);

    public CoupeCarriage(
            int DEFAULT_COUPE_LIMIT,
            boolean allowsGenderSpecificCompartments,
            boolean hasPetFriendlyCompartments) {
        this.DEFAULT_COUPE_LIMIT = DEFAULT_COUPE_LIMIT;
    }

    public CoupeCarriage(
            boolean allowsGenderSpecificCompartments,
            boolean hasPetFriendlyCompartments) {
    }

    @Override
    public double getPriceInByn() {
        int placesInCoupe = 4;
        int price = 20;

        return (price * placesInCoupe);
    }

    @Override
    public int getPassengerCapacity() {
        return Coupe.SEATS_LIMIT;
    }

    @Override
    public int getNumberOfPlaces() {
        return coupes.size();
    }

    @Override
    public List<Seat> getAllSeats() {
        List<Seat> all = new ArrayList<>();
        for (Coupe coupe : coupes) {
            all.addAll(coupe.getAllSeats());
        }
        return all;
    }

    public List<Coupe> getAllCoupes() {
        return coupes;
    }

    @Override
    public int getKgWeight() {
        int COUPE_KG = 800;
        int CARRIAGE_KG = 58000;
        return (COUPE_KG * DEFAULT_COUPE_LIMIT) + CARRIAGE_KG;
    }

    public static class Coupe {
        private final static int SEATS_LIMIT = 4;
        public List<Seat> seats = new ArrayList<>(SEATS_LIMIT);

        public int getNumberOfPlaces() {
            return seats.size();
        }

        public List<Seat> getAllSeats() {
            return seats;
        }

        public int getKgWeight() {
            int SEATS_KG = 11;
            int avPeopleWeight = 62;
            return (SEATS_LIMIT * SEATS_KG) + (getNumberOfPlaces() * avPeopleWeight);
        }
    }

    @Override
    public String toString() {
        return "CoupeCarriage{" +
                "DEFAULT_COUPE_LIMIT=" + DEFAULT_COUPE_LIMIT +
                ", coupes=" + coupes +
                '}';
    }
}
