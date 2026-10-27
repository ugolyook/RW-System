package com.sveta.serialization.impl.mapper;

import com.sveta.locomotive.Locomotive;
import com.sveta.serialization.impl.dto.LocomotiveSerializationDto;
import lombok.Builder;
import lombok.NonNull;

@Builder
public class LocomotiveMapper {
    public static LocomotiveSerializationDto toDto(@NonNull Locomotive locomotive) {
        return LocomotiveSerializationDto.builder()
                .wagonLimit(locomotive.wagonLimit())
                .maxSpeed(locomotive.maxSpeed())
                .maxTransportedWeight(locomotive.maxTransportedWeight())
                .power(locomotive.power())
                .traction(locomotive.tractionForce())
                .weight(locomotive.maxTransportedWeight())
                .isElectric(locomotive.electric())
                .model(locomotive.model())
                .build();
    }

    public static @NonNull Locomotive toDomain(@NonNull LocomotiveSerializationDto dto) {
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