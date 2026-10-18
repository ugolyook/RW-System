package com.sveta.serialization.impl.dto.carriagesDto;

import com.sveta.train.carriage.passenger.SeatedCarriage;
import com.sveta.train.carriage.passenger.models.Seat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SeatedCarriageDto extends CarriageSerializationDto {
    private List<Seat> seats;
    private int baseCarriageWeightKg;

    public SeatedCarriageDto(SeatedCarriage carriage) {
        setSeatsLimit(carriage.getSeatsLimit());
        setWeight(carriage.getKgWeight());
        this.seats = carriage.getSeats();
        this.baseCarriageWeightKg = carriage.getKgWeight();
    }
}