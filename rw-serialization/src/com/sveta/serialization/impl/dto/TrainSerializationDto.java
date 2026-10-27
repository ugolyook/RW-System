package com.sveta.serialization.impl.dto;

import com.sveta.serialization.SuperSerializationDto;
import com.sveta.serialization.impl.dto.carriagesDto.*;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainSerializationDto implements SuperSerializationDto {
    private int trainNumber;
    private LocomotiveSerializationDto locomotive;
    private List<CarriageSerializationDto> carriages;
}