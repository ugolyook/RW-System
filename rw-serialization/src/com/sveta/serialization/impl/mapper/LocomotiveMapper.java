package com.sveta.serialization.impl.mapper;

import com.sveta.locomotive.Locomotive;
import com.sveta.serialization.impl.dto.LocomotiveSerializationDto;

public class LocomotiveMapper {
    public static LocomotiveSerializationDto toDto(Locomotive locomotive) {
        if (locomotive == null) return null;
        LocomotiveSerializationDto dto = new LocomotiveSerializationDto();
        dto.setWagonLimit(locomotive.wagonLimit());
        dto.setMaxSpeed(locomotive.maxSpeed());
        dto.setMaxTransportedWeight(locomotive.maxTransportedWeight());
        dto.setPower(locomotive.power());
        dto.setTraction(locomotive.tractionForce());
        dto.setWeight(locomotive.maxTransportedWeight());
        dto.setElectric(locomotive.electric());
        dto.setModel(locomotive.model());
        return dto;
    }

    public static Locomotive toDomain(LocomotiveSerializationDto dto) {
        if (dto == null) return null;
        return new Locomotive(
                dto.getWagonLimit(),
                dto.getMaxSpeed(),
                dto.getMaxTransportedWeight(),
                dto.getPower(),
                dto.getTraction(),
                dto.isElectric(),
                dto.getModel()
        );
    }
}
