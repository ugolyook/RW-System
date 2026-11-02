package com.sveta.serialization.impl.dto.carriagesDto;

import com.sveta.serialization.SuperSerializationDto;
import com.sveta.train.carriage.passenger.models.SeatType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatSerializationDto implements SuperSerializationDto {
    private int number;
    private SeatType type;
}