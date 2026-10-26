package com.sveta.serialization.impl.mapper;

import com.sveta.serialization.impl.dto.carriagesDto.SeatSerializationDto;
import com.sveta.train.carriage.passenger.models.Seat;
import lombok.Builder;
import lombok.NonNull;

@Builder
public class SeatMapper {
    public static SeatSerializationDto toDto(@NonNull Seat seat) {
        SeatSerializationDto dto = new SeatSerializationDto();
        dto.setNumber(seat.number());
        dto.setType(seat.type());
        return dto;
    }

    public static @NonNull Seat toDomain(@NonNull SeatSerializationDto dto) {
        return new Seat(dto.getNumber(), dto.getType());
    }
}