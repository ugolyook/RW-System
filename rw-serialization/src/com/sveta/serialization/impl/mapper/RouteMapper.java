package com.sveta.serialization.impl.mapper;

import com.sveta.route.Route;
import com.sveta.serialization.impl.dto.RouteSerializationDto;

public class RouteMapper {
    public static RouteSerializationDto toDto(Route route) {
        if (route == null) {
            return null;
        }

        RouteSerializationDto dto = new RouteSerializationDto();
         dto.setDirection(route.direction());
         dto.setStops(route.stops());
        return dto;
    }

    public static Route toDomain(RouteSerializationDto dto) {
        if (dto == null) {
            return null;
        }

        return new Route(
               dto.getStops(),
                dto.getDirection()
        );
    }
}