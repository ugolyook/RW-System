package com.sveta.serialization.impl.dto;

import com.sveta.serialization.SuperSerializationDto;
import com.sveta.serialization.impl.dto.carriagesDto.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TrainSerializationDto implements SuperSerializationDto {
    private int trainNumber;
    private LocomotiveSerializationDto locomotive;
    private List<CarriageSerializationDto> carriages;
}