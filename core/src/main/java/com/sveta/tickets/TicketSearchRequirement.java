package com.sveta.tickets;

import com.sveta.route.Route;
import com.sveta.route.Station;
import com.sveta.route.TrainRun;
import com.sveta.train.Train;
import com.sveta.train.carriage.Carriage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class TicketSearchRequirement {
    private final Station departureStation;
    private final Station arrivalStation;
    private final TrainRun trainRun;
    private final Route route;
    private final LocalDateTime dateTime;
    private final LocalDateTime departureDateTime;
    private final Train train;
    private final Class<? extends Carriage> carriageType;
    private final boolean bicycleRequired;

    public TicketSearchRequirement(
            Station departureStation,
            Station arrivalStation,
            TrainRun trainRun,
            Route route,
            LocalDateTime dateTime,
            LocalDateTime departureDateTime,
            Train train, Class<? extends Carriage> carriageType,
            boolean bicycleRequired
    ) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.trainRun = trainRun;
        this.route = route;
        this.dateTime = dateTime;
        this.departureDateTime = departureDateTime;
        this.train = train;
        this.carriageType = carriageType;
        this.bicycleRequired = bicycleRequired;
    }

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