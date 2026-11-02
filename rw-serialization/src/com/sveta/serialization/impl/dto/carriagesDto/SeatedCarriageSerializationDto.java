package com.sveta.serialization.impl.dto.carriagesDto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SeatedCarriageSerializationDto extends CarriageSerializationDto {
    private List<SeatSerializationDto> seats;
}