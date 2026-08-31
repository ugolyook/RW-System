package com.sveta.carriage.freight;

//для наливных грузов.
public class Tank extends FreightCarriage {
    int maxCargoWeight = 60;
    int cargoVolume = 54;

    public Tank() {}

    public Tank(int maxCargoWeight) {
        this.maxCargoWeight = maxCargoWeight;
    }

    @Override
    public int getKgWeight() {
        return maxCargoWeight;
    }

    public int getCargoVolume() {
        return cargoVolume;
    }
}
