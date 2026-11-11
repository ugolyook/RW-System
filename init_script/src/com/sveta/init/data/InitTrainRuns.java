package com.sveta.init.data;

import com.sveta.route.Route;
import com.sveta.route.TrainRun;
import com.sveta.train.Train;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class InitTrainRuns {
    private final InitRoutes routes;

    public List<TrainRun> create(List<Train> trains) {
        Train full = trains.get(InitTrains.FULL_TRAIN);
        Train seated = trains.get(InitTrains.SEATED_TRAIN);
        Train economy = trains.get(InitTrains.ECONOMY_TRAIN);

        return List.of(
                trainRun(full, routes.minskMogilev(), LocalDateTime.of(2026, 9, 20, 14, 30), true),
                trainRun(full, routes.minskMogilev(), LocalDateTime.of(2026, 9, 21, 14, 30), true),
                trainRun(seated, routes.minskBrest(), LocalDateTime.of(2026, 9, 20, 8, 15), false),
                trainRun(economy, routes.minskGomel(), LocalDateTime.of(2026, 9, 22, 19, 0), false));
    }

    private TrainRun trainRun(Train train, Route route, LocalDateTime departure, boolean express) {
        return TrainRun.builder()
                .train(train)
                .route(route)
                .departureTime(departure)
                .isExpress(express)
                .build();
    }
}