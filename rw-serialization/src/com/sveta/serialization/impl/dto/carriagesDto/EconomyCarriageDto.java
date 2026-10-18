package com.sveta.serialization.impl.dto.carriagesDto;

import com.sveta.train.carriage.passenger.EconomyCarriage;
import com.sveta.train.carriage.passenger.models.Seat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EconomyCarriageDto extends CarriageSerializationDto {
    private int seatsLimit;
    private boolean hasBioToilets;
    private int baseCarriageWeightKg;

    public EconomyCarriageDto(EconomyCarriage carriage) {
        setSeatsLimit(carriage.getSeatsLimit());
        setWeight(carriage.getKgWeight());
        this.seatsLimit = carriage.getSeatsLimit();
        this.hasBioToilets = carriage.isHasBioToilets();
        this.baseCarriageWeightKg = carriage.getKgWeight();
    }
}