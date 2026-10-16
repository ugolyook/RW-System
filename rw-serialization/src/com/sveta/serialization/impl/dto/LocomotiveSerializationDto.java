package com.sveta.serialization.impl.dto;

import com.sveta.locomotive.Locomotive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LocomotiveSerializationDto {
    private String model;
    private double power;
    private double traction;
    private double weight;
    private boolean isElectric;

    public LocomotiveSerializationDto(Locomotive locomotive){
        this.model = locomotive.model();
        this.power = locomotive.power();
        this.traction = locomotive.tractionForce();
        this.weight = locomotive.maxTransportedWeight();
        this.isElectric = locomotive.electric();
    }
}