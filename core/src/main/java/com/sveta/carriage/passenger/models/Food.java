package com.sveta.carriage.passenger.models;

public enum Food {
    BORSCH(8.50),
    STEAK(22.00),
    SALAD(6.00),
    SOUP(7.20),
    TEA(3.50),
    COFFEE(4.80),
    PASTA(11.50),
    FISH(16.30),
    CHEESECAKE(5.90),
    JUICE(4.00);

    private final double price;

    Food(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name() + " (" + price + " руб.)";
    }
}
