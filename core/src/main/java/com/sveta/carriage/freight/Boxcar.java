package com.sveta.carriage.freight;

//для штучных грузов.
public class Boxcar extends FreightCarriage{
    int maxCargoWeight = 66;
    int cargoVolume = 86 ;

    @Override
    public int getKgWeight() {
        return 0;
    }
}
