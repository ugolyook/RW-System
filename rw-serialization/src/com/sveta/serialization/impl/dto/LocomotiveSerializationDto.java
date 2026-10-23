package com.sveta.serialization.impl.dto;

import com.sveta.locomotive.Locomotive;
import com.sveta.serialization.SuperSerializationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LocomotiveSerializationDto implements SuperSerializationDto {
    private int wagonLimit;
    private int maxSpeed;
    private int maxTransportedWeight;
    private int power;
    private int traction;
    private double weight;
    private boolean isElectric;
    private String model;

    public LocomotiveSerializationDto(Locomotive locomotive) {
        this.wagonLimit = locomotive.wagonLimit();
        this.maxSpeed = locomotive.maxSpeed();
        this.maxTransportedWeight = locomotive.maxTransportedWeight();
        this.power = locomotive.power();
        this.traction = locomotive.tractionForce();
        this.weight = locomotive.maxTransportedWeight();
        this.isElectric = locomotive.electric();
        this.model = locomotive.model();
    }
}