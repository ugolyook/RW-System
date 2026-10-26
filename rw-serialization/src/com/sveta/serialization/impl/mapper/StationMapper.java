package com.sveta.serialization.impl.mapper;

import com.sveta.route.Station;
import com.sveta.serialization.impl.dto.StationSerializationDto;
import lombok.Builder;
import lombok.NonNull;

@Builder
public class StationMapper {
    public static StationSerializationDto toDto(@NonNull Station station) {
        StationSerializationDto dto = new StationSerializationDto();
        dto.setStationName(station.stationName());
        dto.setCode(station.code());
        return dto;
    }

    public static @NonNull Station toDomain(@NonNull StationSerializationDto dto) {
        return new Station(dto.getStationName(), dto.getCode());
    }
}