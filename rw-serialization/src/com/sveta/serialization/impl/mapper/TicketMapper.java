package com.sveta.serialization.impl.mapper;

import com.sveta.serialization.impl.dto.TicketDto;
import com.sveta.tickets.Ticket;

public class TicketMapper {
    public static TicketDto toDto(Ticket ticket) {
        if (ticket == null) {
            return null;
        }

        TicketDto dto = new TicketDto();
        dto.setArrivalStation(ticket.arrivalStation());
        dto.setCarriage(ticket.carriage());
        dto.setSeat(ticket.seat());
        dto.setPrice(ticket.price());
        dto.setPassengerName(ticket.passengerName());
        dto.setTrainRun(ticket.trainRun());
        dto.setDepartureStation(ticket.departureStation());
        return dto;
    }

    public static Ticket toDomain(TicketDto dto) {
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