package com.sveta.serialization.impl.dto.carriagesDto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoupeCarriageSerializationDto extends CarriageSerializationDto {
    private int coupeLimit;
    private List<CoupeSerializationDto> coupes;
}