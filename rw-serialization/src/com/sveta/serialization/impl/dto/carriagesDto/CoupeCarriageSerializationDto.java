package com.sveta.serialization.impl.dto.carriagesDto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CoupeCarriageSerializationDto extends CarriageSerializationDto {
    private int coupeLimit;
    private List<CoupeSerializationDto> coupes;
}