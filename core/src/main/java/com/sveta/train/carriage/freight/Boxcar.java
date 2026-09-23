package com.sveta.train.carriage.freight;

//для штучных грузов.
public class Boxcar extends FreightCarriage {
    private final int maxCargoWeightTons;
    private final int baseCarriageWeightInKg;

    public Boxcar(
            int baseCarriageWeightInKg,
            int maxCargoWeightTons
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
