package com.sveta.serialization.impl.mapper;

import com.sveta.route.Station;
import com.sveta.serialization.impl.dto.StationSerializationDto;
import lombok.Builder;
import lombok.NonNull;

public class StationMapper {
    public static StationSerializationDto toDto(@NonNull Station station) {
        return StationSerializationDto.builder()
                .stationName(station.stationName())
                .code(station.code())
                .build();
    }

    public static @NonNull Station toDomain(@NonNull StationSerializationDto dto) {
        return Station.builder()
                .stationName(dto.getStationName())
                .code(dto.getCode())
                .build();
    }
}