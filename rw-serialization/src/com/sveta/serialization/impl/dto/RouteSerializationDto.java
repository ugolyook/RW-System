package com.sveta.serialization.impl.dto;

import com.sveta.route.Directions;
import com.sveta.serialization.SuperSerializationDto;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteSerializationDto implements SuperSerializationDto {
    private List<StationSerializationDto> stops;
    private Directions direction;
}