package com.sveta.carriage.passenger;

import com.sveta.carriage.ElectricCarriage;
import com.sveta.carriage.passenger.models.Seat;

import java.util.ArrayList;
import java.util.List;

public class CoupeCarriage extends PassengerCarriage implements ElectricCarriage {
    private int coupeLimit;
    private int coupeWeightKg;
    private int baseCarriageWeightKg;

    private boolean allowsGenderSpecificCompartments;
    private boolean hasPetFriendlyCompartments;

    private final List<Coupe> coupes;

    public CoupeCarriage(
            int coupeLimit,
            int coupeWeightKg,
            int baseCarriageWeightKg,
            boolean allowsGenderSpecificCompartments,
            boolean hasPetFriendlyCompartments) {
        this.coupeLimit = coupeLimit;
        this.coupeWeightKg = coupeWeightKg;
        this.baseCarriageWeightKg = baseCarriageWeightKg;
        this.allowsGenderSpecificCompartments = allowsGenderSpecificCompartments;
        this.hasPetFriendlyCompartments = hasPetFriendlyCompartments;
        this.coupes = new ArrayList<>(coupeLimit);
    }

    public CoupeCarriage(
            List<Coupe> coupes,
            int coupeWeightKg,
            int baseCarriageWeightKg,
            boolean allowsGenderSpecificCompartments,
            boolean hasPetFriendlyCompartments) {
        this.coupeLimit = coupes.size();
        this.coupeWeightKg = coupeWeightKg;
        this.baseCarriageWeightKg = baseCarriageWeightKg;
        this.allowsGenderSpecificCompartments = allowsGenderSpecificCompartments;
        this.hasPetFriendlyCompartments = hasPetFriendlyCompartments;
        this.coupes = new ArrayList<>(coupes);
    }

    public CoupeCarriage(
            boolean allowsGenderSpecificCompartments,
            boolean hasPetFriendlyCompartments) {
        this(
                9,
                500,
                50000,
                allowsGenderSpecificCompartments,
                hasPetFriendlyCompartments
        );
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

    @Override
    public int getKgWeight() {
        return (coupeWeightKg * coupeLimit) + baseCarriageWeightKg;
    }

    public int getCoupeLimit() {
        return coupeLimit;
    }

    public void setCoupeLimit(int coupeLimit) {
        this.coupeLimit = coupeLimit;
    }

    public int getCoupeWeightKg() {
        return coupeWeightKg;
    }

    public void setCoupeWeightKg(int coupeWeightKg) {
        this.coupeWeightKg = coupeWeightKg;
    }

    public int getBaseCarriageWeightKg() {
        return baseCarriageWeightKg;
    }

    public void setBaseCarriageWeightKg(int baseCarriageWeightKg) {
        this.baseCarriageWeightKg = baseCarriageWeightKg;
    }

    public boolean isAllowsGenderSpecificCompartments() {
        return allowsGenderSpecificCompartments;
    }

    public void setAllowsGenderSpecificCompartments(boolean allowsGenderSpecificCompartments) {
        this.allowsGenderSpecificCompartments = allowsGenderSpecificCompartments;
    }

    public boolean isHasPetFriendlyCompartments() {
        return hasPetFriendlyCompartments;
    }

    public void setHasPetFriendlyCompartments(boolean hasPetFriendlyCompartments) {
        this.hasPetFriendlyCompartments = hasPetFriendlyCompartments;
    }

    public static class Coupe {
        public static final int SEATS_PER_COUPE = 4; // Количество мест в купе по умолчанию

        private int seatWeightKg;
        private int averagePassengerWeightKg;
        public List<Seat> seats;

        public Coupe(int seatWeightKg, int averagePassengerWeightKg) {
            this.seatWeightKg = seatWeightKg;
            this.averagePassengerWeightKg = averagePassengerWeightKg;
            this.seats = new ArrayList<>(SEATS_PER_COUPE);
        }

        public Coupe() {
            this(11, 62);
        }

        public int getNumberOfPlaces() {
            return seats.size();
        }

        public List<Seat> getAllSeats() {
            return seats;
        }

        public int getKgWeight() {
            return (seats.size() * seatWeightKg) + (getNumberOfPlaces() * averagePassengerWeightKg);
        }

        public int getSeatWeightKg() {
            return seatWeightKg;
        }

        public void setSeatWeightKg(int seatWeightKg) {
            this.seatWeightKg = seatWeightKg;
        }

        public int getAveragePassengerWeightKg() {
            return averagePassengerWeightKg;
        }

        public void setAveragePassengerWeightKg(int averagePassengerWeightKg) {
            this.averagePassengerWeightKg = averagePassengerWeightKg;
        }
    }

    @Override
    public String toString() {
        return "CoupeCarriage{" +
                "coupeLimit=" + coupeLimit +
                ", coupeWeightKg=" + coupeWeightKg +
                ", baseCarriageWeightKg=" + baseCarriageWeightKg +
                ", coupes=" + coupes +
                '}';
    }
}
