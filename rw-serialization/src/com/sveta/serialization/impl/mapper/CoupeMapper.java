package com.sveta.serialization.impl.mapper;

import com.sveta.serialization.impl.dto.carriagesDto.CoupeSerializationDto;
import com.sveta.serialization.impl.dto.carriagesDto.SeatSerializationDto;
import com.sveta.train.carriage.passenger.CoupeCarriage;
import com.sveta.train.carriage.passenger.models.Seat;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static main.java.com.sveta.utils.MapListUtils.mapList;
import static main.java.com.sveta.utils.ObjectUtils.applyIfNotNull;

public class CoupeMapper {
    public static CoupeSerializationDto toDto(@NonNull CoupeCarriage.Coupe coupe) {
        return CoupeSerializationDto.builder()
                .seats(mapList(coupe.getSeats(), SeatMapper::toDto))
                .build();
    }

    public static CoupeCarriage.Coupe toDomain(@NonNull CoupeSerializationDto dto) {
        return CoupeCarriage.Coupe.builder()
                .seats(mapList(dto.getSeats(), SeatMapper::toDomain))
                .build();
    }
}