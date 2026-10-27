package com.sveta.serialization.impl.mapper;

import com.sveta.serialization.impl.dto.TicketSerializationDto;
import com.sveta.tickets.Ticket;
import lombok.NonNull;

import static main.java.com.sveta.utils.ObjectUtils.applyIfNotNull;

public class TicketMapper {
    public static TicketSerializationDto toDto(Ticket ticket) {
        TicketSerializationDto dto = new TicketSerializationDto();
        dto.setPassengerName(ticket.passengerName());
        dto.setPrice(ticket.price());

        dto.setDepartureStation(applyIfNotNull(ticket.departureStation(), StationMapper::toDto));
        dto.setArrivalStation(applyIfNotNull(ticket.arrivalStation(), StationMapper::toDto));
        dto.setCarriage(applyIfNotNull(ticket.carriage(), TrainMapper::carriageToDto));
        dto.setSeat(applyIfNotNull(ticket.seat(), SeatMapper::toDto));
        dto.setTrainRun(applyIfNotNull(ticket.trainRun(), TrainRunMapper::toDto));
        return dto;
    }

    public static @NonNull Ticket toDomain(@NonNull TicketSerializationDto dto) {
        return Ticket.builder()
                .passengerName(dto.getPassengerName())
                .price(dto.getPrice())
                .departureStation(applyIfNotNull(dto.getDepartureStation(), StationMapper::toDomain))
                .arrivalStation(applyIfNotNull(dto.getArrivalStation(), StationMapper::toDomain))
                .carriage(applyIfNotNull(dto.getCarriage(), TrainMapper::carriageToDomain))
                .seat(applyIfNotNull(dto.getSeat(), SeatMapper::toDomain))
                .trainRun(applyIfNotNull(dto.getTrainRun(), TrainRunMapper::toDomain))
                .build();
    }
}