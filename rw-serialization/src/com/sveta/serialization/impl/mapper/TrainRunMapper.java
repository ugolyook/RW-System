package com.sveta.serialization.impl.mapper;

import com.sveta.route.TrainRun;
import com.sveta.serialization.impl.dto.RouteDto;
import com.sveta.serialization.impl.dto.TicketDto;
import com.sveta.serialization.impl.dto.TrainRunSerializationDto;
import com.sveta.train.PassengerTrain;

public class TrainRunMapper {
    public static TrainRunSerializationDto toDto(TrainRun trainRun) {
        if (trainRun == null) {
            return null;
        }

        TrainRunSerializationDto dto = new TrainRunSerializationDto();

        if (trainRun.getTrain() instanceof PassengerTrain passengerTrain) {
            dto.setTrain(TrainMapper.toDto(passengerTrain));
        }

        dto.setRoute(trainRun.getRoute() != null ? new RouteDto(trainRun.getRoute()) : null);

        dto.setDepartureTime(trainRun.getDepartureTime());

        if (trainRun.getTickets() != null) {
            dto.setTickets(
                    trainRun.getTickets().stream()
                            .map(TicketDto::new)
                            .toList()
            );
        }

        return dto;
    }

    public static TrainRun toDomain(TrainRunSerializationDto dto) {
        if (dto == null) {
            return null;
        }

        return new TrainRun(
                TrainMapper.toDomain(dto.getTrain()),
                dto.getRoute() != null ? dto.getRoute().toDomain() : null,
                dto.getDepartureTime(),
                dto.getTickets() != null
                        ? dto.getTickets().stream().map(TicketDto::toDomain).toList()
                        : null
        );
    }
}