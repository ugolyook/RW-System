package com.sveta.serialization.impl.dto.carriagesDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EconomyCarriageSerializationDto extends CarriageSerializationDto {
    private boolean hasBioToilets;
    private int baseCarriageWeightKg;
}