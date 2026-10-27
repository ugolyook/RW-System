package com.sveta.serialization.impl.dto.carriagesDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EconomyCarriageSerializationDto extends CarriageSerializationDto {
    private boolean hasBioToilets;
    private int baseCarriageWeightKg;
}