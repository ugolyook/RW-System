package com.sveta.serialization.impl.dto.carriagesDto;

import com.sveta.train.carriage.passenger.models.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiningCarriageDto extends CarriageSerializationDto {
    private int baseCarriageWeightKg;
    private boolean hasHotKitchen;
    private boolean deliveryToTheRoom;
    private List<Food> food;
}