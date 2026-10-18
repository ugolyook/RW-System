package com.sveta.serialization.impl.mapper;

import com.sveta.route.TrainRun;
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

        dto.setRoute(RouteMapper.toDto(trainRun.getRoute()));
        dto.setDepartureTime(trainRun.getDepartureTime());
        dto.setExpress(trainRun.isExpress());

        if (trainRun.getTickets() != null) {
            dto.setTickets(
                    trainRun.getTickets().stream()
                            .map(TicketMapper::toDto)
                            .toList()
            );
        }

        return dto;
    }

    public static TrainRun toDomain(TrainRunSerializationDto dto) {
        if (dto == null) {
            return null;
        }

        TrainRun trainRun = new TrainRun(
                TrainMapper.toDomain(dto.getTrain()),
                RouteMapper.toDomain(dto.getRoute()),
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