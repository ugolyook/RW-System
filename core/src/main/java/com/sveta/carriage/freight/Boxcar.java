package com.sveta.carriage.freight;

//для штучных грузов.
public class Boxcar extends FreightCarriage {
    int maxCargoWeight = 66;
    int cargoVolume = 86;

    public Boxcar() {}

    public Boxcar(int maxCargoWeight) {
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
