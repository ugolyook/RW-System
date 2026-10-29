package com.sveta.serialization.impl.dto;

import com.sveta.serialization.impl.test.SuperSerializationDto;
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
    private double weight;
    private boolean isElectric;
    private String model;
}