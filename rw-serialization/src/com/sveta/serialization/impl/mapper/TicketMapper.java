package com.sveta.serialization.impl.mapper;

import com.sveta.serialization.impl.dto.TicketSerializationDto;
import com.sveta.tickets.Ticket;

public class TicketMapper {
    public static TicketSerializationDto toDto(Ticket ticket) {
        if (ticket == null) {
            return null;
        }

        TicketSerializationDto dto = new TicketSerializationDto();

        dto.setDepartureStation(StationMapper.toDto(ticket.departureStation()));
        dto.setArrivalStation(StationMapper.toDto(ticket.arrivalStation()));
        dto.setCarriage(TrainMapper.carriageToDto(ticket.carriage()));
        dto.setSeat(SeatMapper.toDto(ticket.seat()));
        dto.setTrainRun(ticket.trainRun() != null ? TrainRunMapper.toDto(ticket.trainRun()) : null);
        dto.setPrice(ticket.price());
        dto.setPassengerName(ticket.passengerName());
        return dto;
    }

    public static Ticket toDomain(TicketSerializationDto dto) {
        if (dto == null) {
            return null;
        }

        return new Ticket(
                dto.getPassengerName(),
                dto.getTrainRun() != null ? TrainRunMapper.toDomain(dto.getTrainRun()) : null,
                StationMapper.toDomain(dto.getDepartureStation()),
                StationMapper.toDomain(dto.getArrivalStation()),
                TrainMapper.carriageToDomain(dto.getCarriage()),
                SeatMapper.toDomain(dto.getSeat()),
                dto.getPrice()
        );
    }
}