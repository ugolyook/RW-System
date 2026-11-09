package com.sveta.serialization.impl.mapper;

import com.sveta.route.TrainRun;
import com.sveta.serialization.impl.dto.TrainRunSerializationDto;
import com.sveta.train.PassengerTrain;
import lombok.Builder;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static main.java.com.sveta.utils.ObjectUtils.applyIfNotNull;

@Builder
public class TrainRunMapper {
    public static TrainRunSerializationDto toDto(@NonNull TrainRun trainRun) {
        return getTrainRunSerializationDto(trainRun);
    }

    private static TrainRunSerializationDto getTrainRunSerializationDto(TrainRun trainRun) {
        return TrainRunSerializationDto.builder()
                .train(applyIfNotNull(trainRun.getTrain(), TrainMapper::toDto))
                .route(applyIfNotNull(trainRun.getRoute(), RouteMapper::toDto))
                .departureTime(trainRun.getDepartureTime())
                .isExpress(trainRun.isExpress())
                .tickets(applyIfNotNull(trainRun.getTickets(), list -> list.stream()
                        .map(TicketMapper::toDto)
                        .collect(Collectors.toCollection(ArrayList::new))))
                .build();
    }

    public static @NonNull TrainRun toDomain(@NonNull TrainRunSerializationDto dto) {
        TrainRun trainRun = new TrainRun(
                applyIfNotNull(dto.getTrain(), TrainMapper::toDomain),
                applyIfNotNull(dto.getRoute(), RouteMapper::toDomain),
                dto.getDepartureTime(),
                dto.isExpress()
        );

        if (dto.getTickets() != null) {
            dto.getTickets().forEach(
                    t -> trainRun
                            .addTicket(TicketMapper.toDomain(t, trainRun))  /// TODO
            );
        }

        return trainRun;
    }
}