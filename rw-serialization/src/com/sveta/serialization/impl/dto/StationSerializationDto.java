package com.sveta.serialization.impl.dto;

import com.sveta.serialization.impl.serializer.SuperSerializationDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class StationSerializationDto implements SuperSerializationDto {
    private String stationName;
    private int code;
}