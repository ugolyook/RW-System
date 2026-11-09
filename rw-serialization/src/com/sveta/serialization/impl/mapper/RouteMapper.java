package com.sveta.serialization.impl.mapper;

import com.sveta.route.Route;
import com.sveta.route.Station;
import com.sveta.serialization.impl.dto.RouteSerializationDto;
import lombok.NonNull;

import java.util.List;

import static main.java.com.sveta.utils.MapListUtils.mapList;
import static main.java.com.sveta.utils.ObjectUtils.applyIfNotNull;

public class RouteMapper {
    public static RouteSerializationDto toDto(@NonNull Route route) {
        return getRouteSerializationDto(route);
    }

    private static RouteSerializationDto getRouteSerializationDto(Route route) {
        return RouteSerializationDto.builder()
                .direction(route.direction())
                .stops(mapList(route.stops(), StationMapper::toDto))
                .build();
    }

    public static @NonNull Route toDomain(@NonNull RouteSerializationDto dto) {
        return new Route(mapList(dto.getStops(), StationMapper::toDomain), dto.getDirection());
    }
}