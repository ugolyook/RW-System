package com.sveta.serialization.impl.mapper;

import com.sveta.route.TrainRun;
import com.sveta.serialization.impl.dto.TicketSerializationDto;
import com.sveta.tickets.Ticket;
import lombok.NonNull;

import static main.java.com.sveta.utils.ObjectUtils.applyIfNotNull;

public class TicketMapper {
    public static TicketSerializationDto toDto(@NonNull Ticket ticket) {
        return TicketSerializationDto.builder()
                .passengerName(ticket.passengerName())
                .price(ticket.price())
                .departureStation(applyIfNotNull(ticket.departureStation(), StationMapper::toDto))
                .arrivalStation(applyIfNotNull(ticket.arrivalStation(), StationMapper::toDto))
                .carriage(applyIfNotNull(ticket.carriage(), TrainMapper::carriageToDto))
                .seat(applyIfNotNull(ticket.seat(), SeatMapper::toDto))
                .build();
    }

    public static @NonNull Ticket toDomain(
            @NonNull TicketSerializationDto dto,
            @NonNull TrainRun trainRun
    ) {
        return Ticket.builder()
                .passengerName(dto.getPassengerName())
                .price(dto.getPrice())
                .departureStation(applyIfNotNull(dto.getDepartureStation(), StationMapper::toDomain))
                .arrivalStation(applyIfNotNull(dto.getArrivalStation(), StationMapper::toDomain))
                .carriage(applyIfNotNull(dto.getCarriage(), TrainMapper::carriageToDomain))
                .seat(applyIfNotNull(dto.getSeat(), SeatMapper::toDomain))
                .trainRun(trainRun)
                .build();
    }
}