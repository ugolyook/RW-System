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
        TrainRunSerializationDto dto = new TrainRunSerializationDto();

        if (trainRun.getTrain() instanceof PassengerTrain passengerTrain) {
            dto.setTrain(TrainMapper.toDto(passengerTrain));
        }

        dto.setRoute(applyIfNotNull(trainRun.getRoute(), RouteMapper::toDto));
        dto.setDepartureTime(trainRun.getDepartureTime());
        dto.setExpress(trainRun.isExpress());

        dto.setTickets(applyIfNotNull(trainRun.getTickets(), tickets ->
                tickets.stream()
                        .map(TicketMapper::toDto)
                        .toList()
        ));

        return dto;
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