package com.sveta.serialization.impl.mapper;

import com.sveta.serialization.impl.dto.TicketSerializationDto;
import com.sveta.tickets.Ticket;

public class TicketMapper {
    public static TicketSerializationDto toDto(Ticket ticket) {
        if (ticket == null) {
            return null;
        }

        TicketSerializationDto dto = new TicketSerializationDto();
        dto.setArrivalStation(ticket.arrivalStation());
        dto.setCarriage(ticket.carriage());
        dto.setSeat(ticket.seat());
        dto.setPrice(ticket.price());
        dto.setPassengerName(ticket.passengerName());
        dto.setTrainRun(ticket.trainRun());
        dto.setDepartureStation(ticket.departureStation());
        return dto;
    }

    public static Ticket toDomain(TicketSerializationDto dto) {
        if (dto == null) {
            return null;
        }

        return new Ticket(
                dto.getPassengerName(),
                dto.getTrainRun(),
                dto.getDepartureStation(),
                dto.getArrivalStation(),
                dto.getCarriage(),
                dto.getSeat(),
                dto.getPrice()
        );
    }
}