package com.sveta.serialization.impl.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class StationSerializationDto {
    private String stationName;
    private int code;
}