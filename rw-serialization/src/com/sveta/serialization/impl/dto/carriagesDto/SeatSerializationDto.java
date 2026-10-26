package com.sveta.serialization.impl.dto.carriagesDto;

import com.sveta.train.carriage.passenger.models.SeatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatSerializationDto {
    private int number;
    private SeatType type;
}