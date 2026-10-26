package com.sveta.serialization.impl.mapper;

import com.sveta.route.Route;
import com.sveta.route.Station;
import com.sveta.serialization.impl.dto.RouteSerializationDto;
import com.sveta.serialization.impl.dto.StationSerializationDto;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static main.java.com.sveta.utils.ObjectUtils.applyIfNotNull;

@Builder
public class RouteMapper {
    public static RouteSerializationDto toDto(@NonNull Route route) {
        RouteSerializationDto dto = new RouteSerializationDto();
        dto.setDirection(route.direction());

        dto.setStops(applyIfNotNull(route.stops(), stops ->
                stops.stream()
                        .map(StationMapper::toDto)
                        .toList()
        ));
        return dto;
    }

    public static @NonNull Route toDomain(@NonNull RouteSerializationDto dto) {
        List<Station> stations = applyIfNotNull(dto.getStops(), stops ->
                stops.stream()
                        .map(StationMapper::toDomain)
                        .toList()
        );

        return new Route(stations, dto.getDirection());
    }
}