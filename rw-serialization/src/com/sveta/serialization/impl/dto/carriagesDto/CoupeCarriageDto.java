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
public class CoupeCarriageDto extends CarriageSerializationDto {
    private int coupeLimit;
    private List<CoupeDto> coupes;

}