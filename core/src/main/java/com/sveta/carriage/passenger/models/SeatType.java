package com.sveta.carriage.passenger.models;

public enum SeatType {
    LOWER(1.0),
    UPPER(0.8),
    LOWER_SIDE(0.9),
    UPPER_SIDE(0.7),
    WINDOW(1.0),
    AISLE(1.0),
    BICYCLE(1.5),
    PET(1.0);

    private final double priceCoefficient;

    SeatType(double coefficient) {
        this.priceCoefficient = coefficient;
    }

    public double getPriceCoefficient() {
        return priceCoefficient;
    }
}
