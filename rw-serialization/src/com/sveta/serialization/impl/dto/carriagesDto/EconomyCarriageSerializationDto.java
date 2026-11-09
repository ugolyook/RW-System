package com.sveta.serialization.impl.dto.carriagesDto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EconomyCarriageSerializationDto extends CarriageSerializationDto {
    private boolean hasBioToilets;
}