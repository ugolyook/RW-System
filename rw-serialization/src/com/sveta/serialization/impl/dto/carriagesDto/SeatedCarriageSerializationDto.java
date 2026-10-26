package com.sveta.serialization.impl.dto.carriagesDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatedCarriageSerializationDto extends CarriageSerializationDto {
    private List<SeatSerializationDto> seats;
    private int baseCarriageWeightKg;
}