package com.sveta.serialization.impl.mapper;

import com.sveta.route.TrainRun;
import com.sveta.serialization.impl.dto.TicketSerializationDto;
import com.sveta.serialization.impl.dto.TrainRunSerializationDto;
import com.sveta.train.PassengerTrain;
import lombok.Builder;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static main.java.com.sveta.utils.MapListUtils.mapList;
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
                .tickets(mapList(trainRun.getTickets(), TicketMapper::toDto))
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
            fillTickets(dto.getTickets(), trainRun);
        }

        return trainRun;
    }

    private static void fillTickets(List<TicketSerializationDto> tickets, TrainRun trainRun) {
        tickets.forEach(t -> trainRun.addTicket(TicketMapper.toDomain(t, trainRun))
        );
    }
}