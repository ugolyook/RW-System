package com.sveta.tickets;

import com.sveta.route.Station;
import com.sveta.route.TrainRun;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.models.Seat;

import java.math.BigDecimal;

public record Ticket(
        String passengerName,
        TrainRun trainRun,
        Station departureStation,
        Station arrivalStation,
        Carriage carriage,
        Seat seat,
        BigDecimal price
) {
    @Override
    public String toString() {
        return String.format("TICKET [%s] | Train: %s (%s -> %s) | Carriage: %s, Seat: %s | Price: %s BYN",
                passengerName,
                trainRun.train().getTrainNumber(),
                departureStation.getStationName(),
                arrivalStation.getStationName(),
                carriage.getClass().getSimpleName(),
                seat,
                price);
    }
}
