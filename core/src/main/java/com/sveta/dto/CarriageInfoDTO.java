package com.sveta.dto;

import com.sveta.carriage.Carriage;

import java.util.List;

public class CarriageInfoDTO {
    private final int sizeLimit;
    private final int maxDiningCar;
    private final List<Carriage> carriages;
    private final int lengthLimit;

    public CarriageInfoDTO(
            List<Carriage> carriages,
            int sizeLimit,
            int maxDiningCar,
            int lengthLimit) {
        this.carriages = carriages;
        this.sizeLimit = sizeLimit;
        this.lengthLimit = lengthLimit;
        this.maxDiningCar = maxDiningCar;
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
}
