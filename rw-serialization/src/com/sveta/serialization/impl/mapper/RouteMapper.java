package com.sveta.serialization.impl.mapper;

import com.sveta.route.Route;
import com.sveta.route.Station;
import com.sveta.serialization.impl.dto.RouteSerializationDto;
import com.sveta.serialization.impl.dto.StationSerializationDto;

import java.util.List;

public class RouteMapper {
    public static RouteSerializationDto toDto(Route route) {
        if (route == null) {
            return null;
        }

        RouteSerializationDto dto = new RouteSerializationDto();
        dto.setDirection(route.direction());

        if (route.stops() != null) {
            List<StationSerializationDto> stationDtos = route.stops().stream()
                    .map(StationMapper::toDto)
                    .toList();
            dto.setStops(stationDtos);
        }
        return dto;
    }

    public static Route toDomain(RouteSerializationDto dto) {
        if (dto == null) {
            return null;
        }

        List<Station> stations = null;
        if (dto.getStops() != null) {
            stations = dto.getStops().stream()
                    .map(StationMapper::toDomain)
                    .toList();
        }

        return new Route(stations, dto.getDirection());
    }
}