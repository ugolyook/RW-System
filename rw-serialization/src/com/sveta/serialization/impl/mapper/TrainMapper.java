package com.sveta.serialization.impl.mapper;

import com.sveta.locomotive.Locomotive;
import com.sveta.serialization.impl.dto.LocomotiveSerializationDto;
import com.sveta.serialization.impl.dto.TrainSerializationDto;
import com.sveta.serialization.impl.dto.carriagesDto.*;
import com.sveta.train.PassengerTrain;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.CoupeCarriage;
import com.sveta.train.carriage.passenger.DiningCarriage;
import com.sveta.train.carriage.passenger.EconomyCarriage;
import com.sveta.train.carriage.passenger.SeatedCarriage;

import java.util.List;

public class TrainMapper {
    public static TrainSerializationDto toDto(PassengerTrain train) {
        TrainSerializationDto dto = new TrainSerializationDto();
        dto.setTrainNumber(train.getTrainNumber());
        dto.setLocomotive(new LocomotiveSerializationDto(train.getLocomotive()));

        List<CarriageSerializationDto> carriageDtos = train.getCarriages().stream().map(carriage -> switch (carriage) {
            case CoupeCarriage coupe -> new CoupeCarriageDto(coupe);
            case DiningCarriage dining -> new DiningCarriageDto(dining);
            case SeatedCarriage seated -> new SeatedCarriageDto(seated);
            case EconomyCarriage economy -> new EconomyCarriageDto(economy);
            default ->
                    throw new IllegalArgumentException("Unsupported carriage type: " + carriage.getClass().getName());
        }).toList();

        dto.setCarriages(carriageDtos);
        return dto;
    }

    public static PassengerTrain toDomain(TrainSerializationDto dto) {
        if (dto == null) {
            return null;
        }

        Locomotive locomotive = dto.getLocomotive() != null
                ? dto.getLocomotive().toDomain()
                : null;

        List<Carriage> carriages = dto.getCarriages().stream().map(carriageDto -> switch (carriageDto) {
            case CoupeCarriageDto coupeDto -> coupeDto.toDomain();
            case DiningCarriageDto diningDto -> diningDto.toDomain();
            case SeatedCarriageDto seatedDto -> seatedDto.toDomain();
            case EconomyCarriageDto economyDto -> economyDto.toDomain();
            default -> throw new IllegalArgumentException("Unsupported carriage DTO type: " + carriageDto.getClass().getName());
        }).toList();

        return new PassengerTrain(dto.getTrainNumber(), locomotive, carriages);
    }
}