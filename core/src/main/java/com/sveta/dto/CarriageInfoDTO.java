package com.sveta.dto;

import com.sveta.carriage.Carriage;

import java.util.List;

public record CarriageInfoDTO(
        List<Carriage> carriages,
        int sizeLimit,
        int maxDiningCar,
        int lengthLimit,
        int totalCarriagesWeight) {
}
