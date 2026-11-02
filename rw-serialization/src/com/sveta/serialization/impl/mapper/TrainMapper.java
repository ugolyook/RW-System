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
        return getTrainSerializationDto(train);
    }

    private static TrainSerializationDto getTrainSerializationDto(Train train) {
        return getSerializationDto(train);
    }

    private static TrainSerializationDto getSerializationDto(Train train) {
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
        return getSerializationDto(carriage);
    }

    private static CarriageSerializationDto getSerializationDto(Carriage carriage) {
        return switch (carriage) {
            case CoupeCarriage coupe -> {
                CoupeCarriageSerializationDto dto = new CoupeCarriageSerializationDto();
                dto.setSeatsLimit(coupe.getCoupeLimit());
                dto.setWeight(coupe.getKgWeight());
                dto.setCoupeLimit(coupe.getCoupeLimit());
                yield dto;
            }
            case DiningCarriage dining -> getDiningCarriageSerializationDto(dining);
            case SeatedCarriage seated -> {
                SeatedCarriageSerializationDto dto = new SeatedCarriageSerializationDto();
                dto.setSeatsLimit(seated.getSeatsLimit());
                dto.setWeight(seated.getKgWeight());
                dto.setBaseCarriageWeightKg(seated.getKgWeight());
                yield dto;
            }
            case EconomyCarriage economy -> getEconomyCarriageSerializationDto(economy);
            default ->
                    throw new IllegalArgumentException("Unsupported carriage type: " + carriage.getClass().getName());
        };
    }

    private static EconomyCarriageSerializationDto getEconomyCarriageSerializationDto(EconomyCarriage economy) {
        return EconomyCarriageSerializationDto.builder()
                .hasBioToilets(economy.isHasBioToilets())
                .baseCarriageWeightKg(economy.getKgWeight())
                .build();
    }

    private static DiningCarriageSerializationDto getDiningCarriageSerializationDto(DiningCarriage dining) {
        return DiningCarriageSerializationDto.builder()
                .baseCarriageWeightKg(dining.getKgWeight())
                .hasHotKitchen(dining.isHasHotKitchen())
                .deliveryToTheRoom(dining.isDeliveryToTheRoom())
                .food(dining.getFood())
                .build();
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
            case CoupeCarriageSerializationDto c -> CoupeCarriage.builder()
                    .coupeLimit(c.getCoupeLimit())
                    .build();
            case DiningCarriageSerializationDto d -> DiningCarriage.builder()
                    .seatsLimit(d.getSeatsLimit())
                    .baseCarriageWeightKg(d.getBaseCarriageWeightKg())
                    .hasHotKitchen(d.isHasHotKitchen())
                    .deliveryToTheRoom(d.isDeliveryToTheRoom())
                    .food(d.getFood())
                    .build();
            case SeatedCarriageSerializationDto s -> SeatedCarriage.builder()
                    .seatsLimit(s.getSeatsLimit())
                    .build();
            case EconomyCarriageSerializationDto e -> EconomyCarriage.builder()
                    .seatsLimit(e.getSeatsLimit())
                    .baseCarriageWeightKg(e.getBaseCarriageWeightKg())
                    .hasBioToilets(e.isHasBioToilets())
                    .build();
            default ->
                    throw new IllegalArgumentException("Unsupported carriage DTO: " + carriageDto.getClass().getName());
        };
    }
}