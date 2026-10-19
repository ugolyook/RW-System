package com.sveta.serialization.impl.dto.carriagesDto;

import com.sveta.train.carriage.passenger.EconomyCarriage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EconomyCarriageDto extends CarriageSerializationDto {
    private boolean hasBioToilets;
    private int baseCarriageWeightKg;

    public EconomyCarriageDto(EconomyCarriage carriage) {
        setSeatsLimit(carriage.getSeatsLimit());
        setWeight(carriage.getKgWeight());
        this.hasBioToilets = carriage.isHasBioToilets();
        this.baseCarriageWeightKg = carriage.getKgWeight();
    }
}