package com.sveta.route;

import com.sveta.train.Train;

import java.time.LocalDateTime;

public class TrainRun {
    private final Train train;
    private final Route route;
    private final LocalDateTime departureTime;
    private final boolean isExpress;

    public TrainRun(Train train, Route route, LocalDateTime departureTime, boolean isExpress) {
        this.train = train;
        this.route = route;
        this.departureTime = departureTime;
        this.isExpress = isExpress;
    }
}