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
    private List<Seat> seats;
    boolean hasBioToilets;

    public EconomyCarriageDto(EconomyCarriage carriage) {
        setSeatsLimit(carriage.getSeatsLimit());
        setWeight(carriage.getKgWeight());
        this.seats = carriage.getSeats();
        this.hasBioToilets = carriage.isHasBioToilets();
    }
}