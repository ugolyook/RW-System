package com.sveta.train.carriage.freight;

//для наливных грузов.
public class Tank extends FreightCarriage {
    private final int maxCargoWeightTons;
    private final int baseCarriageWeightInKg;

    public Tank(
            int baseCarriageWeightInKg,
            int maxCargoWeightTons,
            int cargoVolumeCubM
    ) {
        super(baseCarriageWeightInKg);
        this.maxCargoWeightTons = maxCargoWeightTons;
        this.baseCarriageWeightInKg = baseCarriageWeightInKg;
    }

    @Override
    public int getKgWeight() {
        return baseCarriageWeightInKg + (maxCargoWeightTons * 1000);
    }

    @Override
    public int getPassengerCapacity() {
        return 0;
    }
}