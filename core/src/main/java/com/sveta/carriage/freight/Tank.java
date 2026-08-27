package com.sveta.carriage.freight;

//для наливных грузов.
public class Tank extends FreightCarriage{
    int maxCargoWeight =60;
    int cargoVolume = 54;

    @Override
    public int getKgWeight() {
        return 0;
    }
}
