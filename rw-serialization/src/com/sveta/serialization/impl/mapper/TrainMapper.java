package com.sveta.serialization.impl.mapper;

import com.sveta.locomotive.Locomotive;
import com.sveta.serialization.impl.dto.TrainSerializationDto;
import com.sveta.serialization.impl.dto.carriagesDto.*;
import com.sveta.train.PassengerTrain;
import com.sveta.train.Train;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.*;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static main.java.com.sveta.utils.MapListUtils.mapList;
import static main.java.com.sveta.utils.ObjectUtils.applyIfNotNull;

public class TrainMapper {
    public static TrainSerializationDto toDto(@NonNull Train train) {
        return getTrainSerializationDto(train);
    }

    private static TrainSerializationDto getTrainSerializationDto(Train train) {
        return TrainSerializationDto.builder()
                .trainNumber(train.getTrainNumber())
                .locomotive(applyIfNotNull(train.getLocomotive(), LocomotiveMapper::toDto))
                .carriages(mapList(train.getCarriages(), TrainMapper::carriageToDto))
                .build();
    }

    public static CarriageSerializationDto carriageToDto(@NonNull Carriage carriage) {
        return switch (carriage) {
            case CoupeCarriage c -> CoupeCarriageSerializationDto.builder()
                    .seatsLimit(c.getAllSeats().size())
                    .weight(c.getKgWeight())
                    .coupeLimit(c.getCoupeLimit())
                    .coupes(mapList(c.getCoupes(), CoupeMapper::toDto))
                    .build();
            case DiningCarriage dining -> DiningCarriageSerializationDto.builder()
                    .seatsLimit(dining.getSeatsLimit())
                    .weight(dining.getKgWeight())
                    .hasHotKitchen(dining.isHasHotKitchen())
                    .deliveryToTheRoom(dining.isDeliveryToTheRoom())
                    .food(dining.getFood())
                    .build();
            case SeatedCarriage seated -> SeatedCarriageSerializationDto.builder()
                    .seatsLimit(seated.getSeatsLimit())
                    .weight(seated.getKgWeight())
                    .seats(mapList(seated.getSeats(), SeatMapper::toDto))
                    .build();
            case EconomyCarriage economy -> EconomyCarriageSerializationDto.builder()
                    .seatsLimit(economy.getSeatsLimit())
                    .weight(economy.getKgWeight())
                    .hasBioToilets(economy.isHasBioToilets())
                    .build();
            default ->
                    throw new IllegalArgumentException("Unsupported carriage type: " + carriage.getClass().getName());
        };
    }

    public static @NonNull PassengerTrain toDomain(@NonNull TrainSerializationDto dto) {
        Locomotive locomotive = LocomotiveMapper.toDomain(dto.getLocomotive());
        PassengerTrain train = new PassengerTrain(dto.getTrainNumber(), locomotive);

        if (dto.getCarriages() != null) {
            fillCarriages(dto.getCarriages(), train);
        }

        return train;
    }

    private static void fillCarriages(List<CarriageSerializationDto> carriages, PassengerTrain train) {
        carriages.forEach(carriageDto -> train.addCarriage(carriageToDomain(carriageDto))
        );
    }

    public static @NonNull Carriage carriageToDomain(@NonNull CarriageSerializationDto carriageDto) {
        return switch (carriageDto) {
            case CoupeCarriageSerializationDto c -> CoupeCarriage.builder()
                    .coupeLimit(c.getCoupeLimit())
                    .coupes(mapList(c.getCoupes(), CoupeMapper::toDomain))
                    .build();
            case DiningCarriageSerializationDto d -> DiningCarriage.builder()
                    .seatsLimit(d.getSeatsLimit())
                    .hasHotKitchen(d.isHasHotKitchen())
                    .deliveryToTheRoom(d.isDeliveryToTheRoom())
                    .food(d.getFood())
                    .build();
            case SeatedCarriageSerializationDto s -> SeatedCarriage.builder()
                    .seatsLimit(s.getSeatsLimit())
                    .baseCarriageWeightInKg((int) s.getWeight())
                    .build();
            case EconomyCarriageSerializationDto e -> EconomyCarriage.builder()
                    .seatsLimit(e.getSeatsLimit())
                    .hasBioToilets(e.isHasBioToilets())
                    .build();
            default -> throw new IllegalArgumentException(
                    "Unsupported carriage DTO: " + carriageDto.getClass().getName()
            );
        };
    }
}