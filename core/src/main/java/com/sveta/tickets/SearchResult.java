package com.sveta.tickets;

import com.sveta.route.TrainRun;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.models.Seat;

public record SearchResult(
        TrainRun trainRun,
        Carriage carriage,
        Seat seat
) {
    @Override
    public String toString() {
        return String.format("Run: %s, Carriage: %s, Seat: %s",
                trainRun.train().getTrainNumber(),
                carriage.getClass().getSimpleName(),
                seat);
    }
}