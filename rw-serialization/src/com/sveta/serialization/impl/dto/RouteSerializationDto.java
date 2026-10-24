package com.sveta.serialization.impl.dto;

import com.sveta.route.Directions;
import com.sveta.serialization.SuperSerializationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RouteSerializationDto implements SuperSerializationDto {
    private List<StationSerializationDto> stops;
    private Directions direction;
}