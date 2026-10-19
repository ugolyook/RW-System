package com.sveta.serialization.impl.mapper;

import com.sveta.route.Route;
import com.sveta.serialization.impl.dto.RouteDto;

public class RouteMapper {
    public static RouteDto toDto(Route route) {
        if (route == null) {
            return null;
        }

        RouteDto dto = new RouteDto();
         dto.setDirection(route.direction());
         dto.setStops(route.stops());
        return dto;
    }

    public static Route toDomain(RouteDto dto) {
        if (dto == null) {
            return null;
        }

        return new Route(
               dto.getStops(),
                dto.getDirection()
        );
    }
}