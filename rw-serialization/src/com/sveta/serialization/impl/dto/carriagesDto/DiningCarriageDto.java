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
    private List<Food> food;
    private boolean hasHotKitchen;
    private boolean deliveryToTheRoom;

    public DiningCarriageDto(DiningCarriage carriage) {
        setSeatsLimit(carriage.getSeatsLimit());
        setWeight(carriage.getKgWeight());
        this.food = carriage.getFood();
        this.hasHotKitchen = carriage.isHasHotKitchen();
        this.deliveryToTheRoom = carriage.isDeliveryToTheRoom();
    }
}