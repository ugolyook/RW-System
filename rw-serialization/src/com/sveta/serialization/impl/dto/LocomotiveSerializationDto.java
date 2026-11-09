package com.sveta.serialization.impl.dto;

import com.sveta.serialization.impl.serializer.core.SuperSerializationDto;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocomotiveSerializationDto implements SuperSerializationDto {
    private int wagonLimit;
    private int maxSpeed;
    private int maxTransportedWeight;
    private int power;
    private int traction;
    private boolean isElectric;
    private String model;
}