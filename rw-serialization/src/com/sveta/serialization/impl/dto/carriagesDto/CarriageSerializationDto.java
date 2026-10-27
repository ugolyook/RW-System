package com.sveta.serialization.impl.dto.carriagesDto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sveta.serialization.SuperSerializationDto;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CoupeCarriageSerializationDto.class, name = "coupe"),
        @JsonSubTypes.Type(value = SeatedCarriageSerializationDto.class, name = "seated"),
        @JsonSubTypes.Type(value = DiningCarriageSerializationDto.class, name = "dining"),
        @JsonSubTypes.Type(value = EconomyCarriageSerializationDto.class, name = "economy")
})
public abstract class CarriageSerializationDto implements SuperSerializationDto {
    private int seatsLimit;
    private double weight;
}