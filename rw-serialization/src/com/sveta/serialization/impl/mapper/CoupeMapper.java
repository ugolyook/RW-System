package com.sveta.serialization.impl.mapper;

import com.sveta.serialization.impl.dto.carriagesDto.CoupeSerializationDto;
import com.sveta.train.carriage.passenger.CoupeCarriage;
import lombok.NonNull;

import java.util.ArrayList;
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
}