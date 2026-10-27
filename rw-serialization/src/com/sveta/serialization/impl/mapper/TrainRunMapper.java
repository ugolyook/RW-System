package com.sveta.serialization.impl.mapper;

import com.sveta.route.TrainRun;
import com.sveta.serialization.impl.dto.TrainRunSerializationDto;
import com.sveta.train.PassengerTrain;
import lombok.Builder;
import lombok.NonNull;

import static main.java.com.sveta.utils.ObjectUtils.applyIfNotNull;

@Builder
public class TrainRunMapper {
    public static TrainRunSerializationDto toDto(@NonNull TrainRun trainRun) {
        return TrainRunSerializationDto.builder()
                .train(applyIfNotNull(trainRun.getTrain(), train -> {
                    if (train instanceof PassengerTrain passengerTrain) {
                        return TrainMapper.toDto(passengerTrain);
                    }
                    return null;
                }))
                .route(applyIfNotNull(trainRun.getRoute(), RouteMapper::toDto))
                .departureTime(trainRun.getDepartureTime())
                .isExpress(trainRun.isExpress())
                .tickets(applyIfNotNull(trainRun.getTickets(), tickets ->
                        tickets.stream()
                                .map(TicketMapper::toDto)
                                .toList()
                ))
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
            dto.getTickets().stream()
                    .map(TicketMapper::toDomain)
                    .forEach(trainRun::addTicket);
        }

        return trainRun;
    }
}