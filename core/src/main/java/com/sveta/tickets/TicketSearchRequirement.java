package com.sveta.tickets;

import com.sveta.route.Route;
import com.sveta.route.Station;
import com.sveta.route.TrainRun;
import com.sveta.train.Train;
import com.sveta.train.carriage.Carriage;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TicketSearchRequirement {
    private final Station departureStation;
    private final Station arrivalStation;
    private final TrainRun trainRun;
    private final Route route;
    private final LocalDateTime dateTime;
    private final LocalDateTime departureDateTime;
    private final Train train;
    private final Class<? extends Carriage> carriageType;

    public TicketSearchRequirement(Station departureStation, Station arrivalStation, TrainRun trainRun, Route route, LocalDateTime dateTime, LocalDateTime departureDateTime, Train train, Class<? extends Carriage> carriageType) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.trainRun = trainRun;
        this.route = route;
        this.dateTime = dateTime;
        this.departureDateTime = departureDateTime;
        this.train = train;
        this.carriageType = carriageType;
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public Station getArrivalStation() {
        return arrivalStation;
    }

    public TrainRun getTrainRun() {
        return trainRun;
    }

    public Route getRoute() {
        return route;
    }

    public Train getTrain() {
        return train;
    }

    public LocalDate getDepartureDate() {
        if (departureDateTime != null) {
            return departureDateTime.toLocalDate();
        }
        return dateTime != null ? dateTime.toLocalDate() : null;
    }

    public Class<? extends Carriage> getCarriageType() {
        return carriageType;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }
}