package com.sveta.carriage.passenger;

import com.sveta.carriage.passenger.models.Seat;

import java.util.ArrayList;
import java.util.List;

public class CoupeCarriage extends PassengerCarriage {
    private final static int DEFAULT_COUPE_LIMIT = 9;

    private final List<Coupe> coupes = new ArrayList<>(DEFAULT_COUPE_LIMIT);

    private boolean allowsGenderSpecificCompartments;
    private boolean hasPetFriendlyCompartments;

    @Override
    public int getNumberOfPlaces() {
        return 0;
    }

    @Override
    public List<Seat> getAllSeats() {
        return List.of();
    }

    @Override
    public int getKgWeight() {
        return 0;
    }

    public static class Coupe {
        public int place;
    }
}
