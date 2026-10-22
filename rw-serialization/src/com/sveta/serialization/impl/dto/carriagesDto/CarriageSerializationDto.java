package com.sveta.serialization.impl.dto.carriagesDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public abstract class CarriageSerializationDto {
    private int seatsLimit;
    private double weight;
}