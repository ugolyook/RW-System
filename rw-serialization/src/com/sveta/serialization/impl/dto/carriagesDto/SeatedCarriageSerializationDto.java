package com.sveta.serialization.impl.dto.carriagesDto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatedCarriageSerializationDto extends CarriageSerializationDto {
    private List<SeatSerializationDto> seats;
    private int baseCarriageWeightKg;
}