package com.sveta.serialization.impl.dto;

import com.sveta.serialization.SuperSerializationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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