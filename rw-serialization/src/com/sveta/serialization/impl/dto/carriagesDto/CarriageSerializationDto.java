package com.sveta.serialization.impl.dto.carriagesDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class CarriageSerializationDto {
    private int seatsLimit;
    private double weight;
}