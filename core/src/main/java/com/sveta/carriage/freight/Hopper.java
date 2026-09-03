package com.sveta.carriage.freight;

// для сыпучих грузов.
public class Hopper extends FreightCarriage{
    int maxCargoWeight = 70;
    int cargoVolume = 60;

    public Hopper() {}

    public Hopper(int maxCargoWeight) {
        this.maxCargoWeight = maxCargoWeight;
    }

    @Override
    public int getKgWeight() {
        return maxCargoWeight;
    }

    @Override
    public int getPassengerCapacity() {
        return 0;
    }

    public int getCargoVolume() {
        return cargoVolume;
    }
}
