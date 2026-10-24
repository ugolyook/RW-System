package com.sveta.serialization.impl.mapper;

import com.sveta.locomotive.Locomotive;
import com.sveta.serialization.impl.dto.TrainSerializationDto;
import com.sveta.serialization.impl.dto.carriagesDto.*;
import com.sveta.train.PassengerTrain;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.*;

import java.util.List;

public class TrainMapper {
    public static TrainSerializationDto toDto(PassengerTrain train) {
        if (train == null) return null;

        TrainSerializationDto dto = new TrainSerializationDto();
        dto.setTrainNumber(train.getTrainNumber());

        dto.setLocomotive(LocomotiveMapper.toDto(train.getLocomotive()));
        List<CarriageSerializationDto> carriageDtos = train.getCarriages().stream()
                .map(TrainMapper::carriageToDto)
                .toList();

        dto.setCarriages(carriageDtos);
        return dto;
    }

    public static CarriageSerializationDto carriageToDto(Carriage carriage) {
        if (carriage == null) return null;

        return getCarriageSerializationDto(carriage);
    }

    private static CarriageSerializationDto getCarriageSerializationDto(Carriage carriage) {
        return switch (carriage) {
            case CoupeCarriage coupe -> {
                CoupeCarriageDto dto = new CoupeCarriageDto();
                dto.setSeatsLimit(coupe.getCoupeLimit());
                dto.setWeight(coupe.getKgWeight());
                dto.setCoupeLimit(coupe.getCoupeLimit());
                yield dto;
            }
            case DiningCarriage dining -> {
                DiningCarriageDto dto = new DiningCarriageDto();
                dto.setSeatsLimit(dining.getSeatsLimit());
                dto.setWeight(dining.getKgWeight());
                dto.setBaseCarriageWeightKg(dining.getKgWeight());
                dto.setHasHotKitchen(dining.isHasHotKitchen());
                dto.setDeliveryToTheRoom(dining.isDeliveryToTheRoom());
                dto.setFood(dining.getFood());
                yield dto;
            }
            case SeatedCarriage seated -> {
                SeatedCarriageDto dto = new SeatedCarriageDto();
                dto.setSeatsLimit(seated.getSeatsLimit());
                dto.setWeight(seated.getKgWeight());
                dto.setBaseCarriageWeightKg(seated.getKgWeight());
                yield dto;
            }
            case EconomyCarriage economy -> {
                EconomyCarriageDto dto = new EconomyCarriageDto();
                dto.setSeatsLimit(economy.getSeatsLimit());
                dto.setWeight(economy.getKgWeight());
                dto.setHasBioToilets(economy.isHasBioToilets());
                dto.setBaseCarriageWeightKg(economy.getKgWeight());
                yield dto;
            }
            default ->
                    throw new IllegalArgumentException("Unsupported carriage type: " + carriage.getClass().getName());
        };
    }

    public static PassengerTrain toDomain(TrainSerializationDto dto) {
        if (dto == null) return null;

        Locomotive locomotive = LocomotiveMapper.toDomain(dto.getLocomotive());
        PassengerTrain train = new PassengerTrain(dto.getTrainNumber(), locomotive);

        if (dto.getCarriages() != null) {
            dto.getCarriages().forEach(carriageDto ->
                    train.addCarriage(carriageToDomain(carriageDto)));
        }

        return train;
    }

    public static Carriage carriageToDomain(CarriageSerializationDto carriageDto) {
        return switch (carriageDto) {
            case CoupeCarriageDto c -> new CoupeCarriage(
                    c.getCoupes() != null ? c.getCoupes().size() : 0,
                    c.getCoupeLimit()
            );
            case DiningCarriageDto d -> new DiningCarriage(
                    d.getSeatsLimit(), d.getBaseCarriageWeightKg(),
                    d.isHasHotKitchen(), d.isDeliveryToTheRoom(), d.getFood()
            );
            case SeatedCarriageDto s -> new SeatedCarriage(s.getSeatsLimit(), s.getBaseCarriageWeightKg());
            case EconomyCarriageDto e -> new EconomyCarriage(
                    e.getSeatsLimit(), e.getBaseCarriageWeightKg(),
                    e.isHasBioToilets()
            );
            default -> throw new IllegalArgumentException("Unsupported carriage DTO: " + carriageDto.getClass().getName());
        };
    }
}