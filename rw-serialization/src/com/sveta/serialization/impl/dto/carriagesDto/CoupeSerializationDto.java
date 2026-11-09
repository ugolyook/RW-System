package com.sveta.serialization.impl.dto.carriagesDto;

import com.sveta.serialization.impl.serializer.core.SuperSerializationDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Builder
public class CoupeSerializationDto implements SuperSerializationDto {
    private List<SeatSerializationDto> seats;
}