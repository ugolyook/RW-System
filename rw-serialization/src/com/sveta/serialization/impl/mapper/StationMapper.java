package com.sveta.serialization.impl.mapper;

import com.sveta.route.Station;
import com.sveta.serialization.impl.dto.StationSerializationDto;

public class StationMapper {
    public static StationSerializationDto toDto(Station station) {
        if (station == null) return null;
        StationSerializationDto dto = new StationSerializationDto();
        dto.setStationName(station.stationName());
        dto.setCode(station.code());
        return dto;
    }

    public static Station toDomain(StationSerializationDto dto) {
        if (dto == null) return null;
        return new Station(dto.getStationName(), dto.getCode());
    }
}