package com.sveta.serialization.impl.dto.carriagesDto;

import com.sveta.train.carriage.passenger.models.Food;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DiningCarriageSerializationDto extends CarriageSerializationDto {
    private boolean hasHotKitchen;
    private boolean deliveryToTheRoom;
    private List<Food> food;
}