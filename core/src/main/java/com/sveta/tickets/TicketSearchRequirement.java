package com.sveta.tickets;

import com.sveta.route.Route;
import com.sveta.route.Station;
import com.sveta.route.TrainRun;
import com.sveta.train.Train;
import com.sveta.train.carriage.Carriage;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record TicketSearchRequirement(
        Station departureStation,
        Station arrivalStation,
        TrainRun trainRun,
        Route route,
        LocalDateTime dateTime,
        LocalDateTime departureDateTime,
        Train train,
        Class<? extends Carriage> carriageType,
        boolean bicycleRequired
) {

    public TicketSearchRequirement(
            Station departureStation,
            Station arrivalStation,
            TrainRun trainRun,
            Route route,
            LocalDate departureDate,
            LocalDateTime departureDateTime,
            Train train,
            Class<? extends Carriage> carriageType
    ) {
        this(departureStation,
                arrivalStation,
                trainRun,
                route,
                departureDate != null ? departureDate.atStartOfDay() : null,
                departureDateTime,
                train,
                carriageType,
                false);
    }

    public LocalDate getDepartureDate() {
        if (departureDateTime != null) {
            return departureDateTime.toLocalDate();
        }
        return dateTime != null ? dateTime.toLocalDate() : null;
    }
}