package com.sveta.serialization.impl.dto.carriagesDto;

import com.sveta.train.carriage.passenger.CoupeCarriage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CoupeCarriageDto extends CarriageSerializationDto {
    private int coupeLimit;
    private List<CoupeDto> coupes;

    public CoupeCarriageDto(CoupeCarriage carriage) {
        setSeatsLimit(carriage.getCoupeLimit());
        setWeight(carriage.getKgWeight());
        this.coupeLimit = carriage.getCoupeLimit();
    }
}