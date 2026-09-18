package com.sveta.train.carriage.freight;

// для сыпучих грузов.
public class Hopper extends FreightCarriage{
    int maxCargoWeightTons;
    int cargoVolumeCubM;

    public Hopper(
            int baseCarriageWeightInKg,
            int maxCargoWeightTons,
            int cargoVolumeCubM
    ) {
        super(baseCarriageWeightInKg);
        this.maxCargoWeightTons = maxCargoWeightTons;
        this.cargoVolumeCubM = cargoVolumeCubM;
    }
    @Override
    public int getKgWeight() {
        return maxCargoWeightTons;
    }

    @Override
    public int getPassengerCapacity() {
        return 0;
    }
}