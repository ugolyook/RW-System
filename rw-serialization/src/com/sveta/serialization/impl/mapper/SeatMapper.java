package com.sveta.serialization.impl.mapper;

import com.sveta.serialization.impl.dto.carriagesDto.SeatDto;
import com.sveta.train.carriage.passenger.models.Seat;

public class SeatMapper {
    public static SeatDto toDto(Seat seat) {
        if (seat == null) return null;
        SeatDto dto = new SeatDto();
        dto.setNumber(seat.number());
        dto.setType(seat.type());
        return dto;
    }

    public static Seat toDomain(SeatDto dto) {
        if (dto == null) return null;
        return new Seat(dto.getNumber(), dto.getType());
    }
}