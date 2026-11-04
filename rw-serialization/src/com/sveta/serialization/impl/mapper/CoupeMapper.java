package com.sveta.serialization.impl.mapper;

import com.sveta.serialization.impl.dto.carriagesDto.CoupeSerializationDto;
import com.sveta.train.carriage.passenger.CoupeCarriage;
import com.sveta.train.carriage.passenger.models.Seat;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static main.java.com.sveta.utils.ObjectUtils.applyIfNotNull;

public class CoupeMapper {
    public static CoupeSerializationDto toDto(@NonNull CoupeCarriage.Coupe coupe) {
        return CoupeSerializationDto.builder()
                .seats(applyIfNotNull(coupe.getSeats(), list -> list.stream()
                        .map(SeatMapper::toDto)
                        .collect(Collectors.toCollection(ArrayList::new))))
                .build();
    }

    public static CoupeCarriage.Coupe toDomain(@NonNull CoupeSerializationDto dto) {
        List<Seat> seats = applyIfNotNull(dto.getSeats(),
                list -> list.stream()
                        .map(SeatMapper::toDomain)
                        .toList());
        return CoupeCarriage.Coupe.builder()
                .seats(seats)
                .build();
    }
}