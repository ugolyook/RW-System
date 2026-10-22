package com.sveta.route;

import com.sveta.tickets.Ticket;
import com.sveta.train.Train;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.models.Seat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Builder
@Getter
@Setter
public class TrainRun {
    private static final Logger log = LoggerFactory.getLogger(TrainRun.class);

    private final Train train;
    private final Route route;
    private final LocalDateTime departureTime;
    private final boolean isExpress;
    private final List<Ticket> tickets = new ArrayList<>();

    public TrainRun(Train train, Route route, LocalDateTime departureTime, boolean isExpress) {
        this.train = train;
        this.route = route;
        this.departureTime = departureTime;
        this.isExpress = isExpress;
    }

    public List<Ticket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }

    public void addTicket(Ticket ticket) {
        if (isSeatOccupied(ticket.carriage(), ticket.seat())) {
            throw new IllegalStateException("Place" + ticket.seat().number() + " was booked");
        }
        this.tickets.add(ticket);
        log.info("Ticket successfully added to TrainRun. Passenger: '{}', Carriage: {}, Seat: №{}, Total tickets on run: {}",
                ticket.passengerName(),
                ticket.carriage().getClass().getSimpleName(),
                ticket.seat() != null ? ticket.seat().number() : "N/A",
                tickets.size());
    }

    public boolean isSeatOccupied(Carriage carriage, Seat seat) {
        if (seat == null) {
            return false;
        }
        return tickets.stream()
                .anyMatch(ticket -> ticket.carriage().equals(carriage)
                        && ticket.seat().equals(seat));
    }
}