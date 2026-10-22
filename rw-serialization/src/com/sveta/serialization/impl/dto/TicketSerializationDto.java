package com.sveta.serialization.impl.dto;

import com.sveta.route.Station;
import com.sveta.route.TrainRun;
import com.sveta.tickets.Ticket;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.models.Seat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class TicketSerializationDto {
    private String passengerName;
    private TrainRun trainRun;
    private Station departureStation;
    private Station arrivalStation;
    private Carriage carriage;
    private Seat seat;
    private BigDecimal price;

    public TicketSerializationDto(Ticket ticket) {
        this.passengerName = ticket.passengerName();
        this.trainRun = ticket.trainRun();
        this.departureStation = ticket.departureStation();
        this.arrivalStation = ticket.arrivalStation();
        this.carriage = ticket.carriage();
        this.seat = ticket.seat();
        this.price = ticket.price();
    }
}