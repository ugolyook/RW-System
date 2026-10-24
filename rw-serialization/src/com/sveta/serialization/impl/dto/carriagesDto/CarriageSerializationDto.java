package com.sveta.serialization.impl.dto.carriagesDto;

import com.sveta.serialization.SuperSerializationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class CarriageSerializationDto implements SuperSerializationDto {
    private int seatsLimit;
    private double weight;
}