package com.sveta.serialization.impl.dto;

import com.sveta.route.Directions;
import com.sveta.route.Route;
import com.sveta.route.Station;
import com.sveta.serialization.SuperSerializationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class RouteSerializationDto implements SuperSerializationDto {
    private List<Station> stops;
    private Directions direction;

    public RouteSerializationDto(Route route) {
        this.stops = route.stops();
        this.direction = route.direction();
    }
}