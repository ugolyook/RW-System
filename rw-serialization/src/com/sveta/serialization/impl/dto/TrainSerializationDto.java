package com.sveta.serialization.impl.dto;

import com.sveta.serialization.impl.dto.carriagesDto.*;
import com.sveta.train.Train;
import com.sveta.train.carriage.passenger.CoupeCarriage;
import com.sveta.train.carriage.passenger.DiningCarriage;
import com.sveta.train.carriage.passenger.EconomyCarriage;
import com.sveta.train.carriage.passenger.SeatedCarriage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TrainSerializationDto {
    private int trainNumber;
    private LocomotiveSerializationDto locomotive;
    private List<CarriageSerializationDto> carriages;


    public TrainSerializationDto(Train train){
        this.trainNumber = train.getTrainNumber();
        this.locomotive = new LocomotiveSerializationDto(train.getLocomotive());
        this.carriages = train.getCarriages().stream()
                .map(carriage -> {
                    if (carriage instanceof CoupeCarriage coupe) {
                        return new CoupeCarriageDto(coupe);
                    } else if (carriage instanceof DiningCarriage dining) {
                        return new DiningCarriageDto(dining);
                    } else if (carriage instanceof SeatedCarriage seated) {
                        return new SeatedCarriageDto(seated);
                    } else if (carriage instanceof EconomyCarriage economy) {
                        return new EconomyCarriageDto(economy);
                    }
                    throw new IllegalArgumentException("Unknown carriage type: " + carriage.getClass().getName());
                })
                .toList();
    }
}