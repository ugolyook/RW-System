package com.sveta.route;

import com.sveta.train.Train;

import java.time.LocalDateTime;

public record TrainRun(
        Train train,
        Route route,
        LocalDateTime departureTime,
        boolean isExpress
) {
}