package com.sveta.serialization.impl.mapper;

import com.sveta.locomotive.Locomotive;
import com.sveta.serialization.impl.dto.LocomotiveSerializationDto;
import com.sveta.serialization.impl.dto.TrainSerializationDto;
import com.sveta.serialization.impl.dto.carriagesDto.*;
import com.sveta.train.PassengerTrain;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.*;

import java.util.List;

public class TrainMapper {
    public static TrainSerializationDto toDto(PassengerTrain train) {
        TrainSerializationDto dto = new TrainSerializationDto();
        dto.setTrainNumber(train.getTrainNumber());
        dto.setLocomotive(new LocomotiveSerializationDto(train.getLocomotive()));

        List<CarriageSerializationDto> carriageDtos = getCarriageSerializationDtos(train);

        dto.setCarriages(carriageDtos);
        return dto;
    }

    private static List<CarriageSerializationDto> getCarriageSerializationDtos(PassengerTrain train) {
        return train.getCarriages().stream().map(carriage -> switch (carriage) {
            case CoupeCarriage coupe -> new CoupeCarriageDto(coupe);
            case DiningCarriage dining -> new DiningCarriageDto(dining);
            case SeatedCarriage seated -> new SeatedCarriageDto(seated);
            case EconomyCarriage economy -> new EconomyCarriageDto(economy);
            default ->
                    throw new IllegalArgumentException("Unsupported carriage type: " + carriage.getClass().getName());
        }).toList();
    }

    public static PassengerTrain toDomain(TrainSerializationDto dto) {
        if (dto == null) return null;

        Locomotive locomotive = LocomotiveMapper.toDomain(dto.getLocomotive());

        PassengerTrain train = new PassengerTrain(dto.getTrainNumber(), locomotive);

        getCarriagesDomainDto(dto, train);

        return train;
    }

    private static void getCarriagesDomainDto(TrainSerializationDto dto, PassengerTrain train) {
        if (dto.getCarriages() != null) {
            dto.getCarriages().forEach(carriageDto -> {
                Carriage carriage = switch (carriageDto) {
                    case CoupeCarriageDto c -> new CoupeCarriage(
                            c.getCoupes() != null ? c.getCoupes().size() : 0,
                            c.getCoupeLimit());
                    case DiningCarriageDto d -> new DiningCarriage(
                            d.getSeatsLimit(), d.getBaseCarriageWeightKg(),
                            d.isHasHotKitchen(), d.isDeliveryToTheRoom(), d.getFood()
                    );
                    case SeatedCarriageDto s -> new SeatedCarriage(s.getSeatsLimit(), s.getBaseCarriageWeightKg());
                    case EconomyCarriageDto e -> new EconomyCarriage(
                            e.getSeatsLimit(), e.getBaseCarriageWeightKg(),
                            e.isHasBioToilets()
                    );
                    default ->
                            throw new IllegalArgumentException("Unsupported carriage DTO: " + carriageDto.getClass().getName());
                };
                train.addCarriage(carriage);
            });
        }
    }
}