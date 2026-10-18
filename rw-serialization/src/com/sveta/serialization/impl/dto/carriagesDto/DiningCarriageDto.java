package com.sveta.serialization.impl.dto.carriagesDto;

import com.sveta.train.carriage.passenger.DiningCarriage;
import com.sveta.train.carriage.passenger.models.Food;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DiningCarriageDto extends CarriageSerializationDto {
    private int baseCarriageWeightKg;
    private boolean hasHotKitchen;
    private boolean deliveryToTheRoom;
    private List<Food> food;

    public DiningCarriageDto(DiningCarriage carriage) {
        setSeatsLimit(carriage.getSeatsLimit());
        setWeight(carriage.getKgWeight());
        this.baseCarriageWeightKg = carriage.getKgWeight();
        this.hasHotKitchen = carriage.isHasHotKitchen();
        this.deliveryToTheRoom = carriage.isDeliveryToTheRoom();
        this.food = carriage.getFood();
    }
}