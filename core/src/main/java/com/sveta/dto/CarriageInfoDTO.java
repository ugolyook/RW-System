package com.sveta.dto;

import com.sveta.carriage.Carriage;

import java.util.List;

public class CarriageInfoDTO {
    private final int sizeLimit;
    private final int maxDiningCar;
    private final List<Carriage> carriages;
    private final int lengthLimit;
    private final int totalCarriagesWeight;

    public CarriageInfoDTO(
            List<Carriage> carriages,
            int sizeLimit,
            int maxDiningCar,
            int lengthLimit,
            int totalCarriagesWeight) {
        this.carriages = carriages;
        this.sizeLimit = sizeLimit;
        this.lengthLimit = lengthLimit;
        this.maxDiningCar = maxDiningCar;
        this.totalCarriagesWeight = totalCarriagesWeight;
    }

    public int getLengthLimit() {
        return lengthLimit;
    }

    public int getMaxDiningCar() {
        return maxDiningCar;
    }

    public List<Carriage> getCarriages() {
        return carriages;
    }

    public int getSizeLimit() {
        return sizeLimit;
    }

    public int getTotalCarriagesWeight() {
        return totalCarriagesWeight;
    }
}
