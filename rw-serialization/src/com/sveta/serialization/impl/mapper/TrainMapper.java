package com.sveta.serialization.impl.mapper;

import com.sveta.locomotive.Locomotive;
import com.sveta.serialization.impl.dto.TrainSerializationDto;
import com.sveta.serialization.impl.dto.carriagesDto.*;
import com.sveta.train.PassengerTrain;
import com.sveta.train.Train;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.*;
import lombok.NonNull;


import static main.java.com.sveta.utils.ObjectUtils.applyIfNotNull;

public class TrainMapper {
    public static TrainSerializationDto toDto(@NonNull Train train) {
        return TrainSerializationDto.builder()
                .trainNumber(train.getTrainNumber())
                .locomotive(applyIfNotNull(train.getLocomotive(), LocomotiveMapper::toDto))
                .carriages(applyIfNotNull(train.getCarriages(), carriages ->
                        carriages.stream()
                                .map(TrainMapper::carriageToDto)
                                .toList()
                ))
                .build();
    }

    public static CarriageSerializationDto carriageToDto(@NonNull Carriage carriage) {
        return getCarriageSerializationDto(carriage);
    }

    private static CarriageSerializationDto getCarriageSerializationDto(Carriage carriage) {
        return switch (carriage) {
            case CoupeCarriage coupe -> {
                CoupeCarriageSerializationDto dto = new CoupeCarriageSerializationDto();
                dto.setSeatsLimit(coupe.getCoupeLimit());
                dto.setWeight(coupe.getKgWeight());
                dto.setCoupeLimit(coupe.getCoupeLimit());
                yield dto;
            }
            case DiningCarriage dining -> {
                DiningCarriageSerializationDto dto = new DiningCarriageSerializationDto();
                dto.setSeatsLimit(dining.getSeatsLimit());
                dto.setWeight(dining.getKgWeight());
                dto.setBaseCarriageWeightKg(dining.getKgWeight());
                dto.setHasHotKitchen(dining.isHasHotKitchen());
                dto.setDeliveryToTheRoom(dining.isDeliveryToTheRoom());
                dto.setFood(dining.getFood());
                yield dto;
            }
            case SeatedCarriage seated -> {
                SeatedCarriageSerializationDto dto = new SeatedCarriageSerializationDto();
                dto.setSeatsLimit(seated.getSeatsLimit());
                dto.setWeight(seated.getKgWeight());
                dto.setBaseCarriageWeightKg(seated.getKgWeight());
                yield dto;
            }
            case EconomyCarriage economy -> {
                EconomyCarriageSerializationDto dto = new EconomyCarriageSerializationDto();
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

    public static @NonNull PassengerTrain toDomain(@NonNull TrainSerializationDto dto) {
        Locomotive locomotive = LocomotiveMapper.toDomain(dto.getLocomotive());
        PassengerTrain train = new PassengerTrain(dto.getTrainNumber(), locomotive);

        if (dto.getCarriages() != null) {
            dto.getCarriages().forEach(carriageDto ->
                    train.addCarriage(carriageToDomain(carriageDto)));
        }

        return train;
    }

    public static @NonNull Carriage carriageToDomain(@NonNull CarriageSerializationDto carriageDto) {
        return switch (carriageDto) {
            case CoupeCarriageSerializationDto c -> new CoupeCarriage(
                    c.getCoupes() != null ? c.getCoupes().size() : 0,
                    c.getCoupeLimit()
            );
            case DiningCarriageSerializationDto d -> new DiningCarriage(
                    d.getSeatsLimit(), d.getBaseCarriageWeightKg(),
                    d.isHasHotKitchen(), d.isDeliveryToTheRoom(), d.getFood()
            );
            case SeatedCarriageSerializationDto s -> new SeatedCarriage(s.getSeatsLimit(), s.getBaseCarriageWeightKg());
            case EconomyCarriageSerializationDto e -> new EconomyCarriage(
                    e.getSeatsLimit(), e.getBaseCarriageWeightKg(),
                    e.isHasBioToilets()
            );
            default ->
                    throw new IllegalArgumentException("Unsupported carriage DTO: " + carriageDto.getClass().getName());
        };
    }
}