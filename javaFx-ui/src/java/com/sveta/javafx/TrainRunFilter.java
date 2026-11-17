package com.sveta.javafx;

import com.sveta.route.TrainRun;

import java.time.LocalDate;
import java.util.function.Predicate;

public record TrainRunFilter(
        String query,
        String type,
        String direction,
        LocalDate date,
        boolean specialSeatsOnly
) implements Predicate<TrainRun> {

    public static final String ALL_TYPES = "All types";
    public static final String ALL_DIRECTIONS = "All directions";

    public static final TrainRunFilter NONE =
            new TrainRunFilter(null, ALL_TYPES, ALL_DIRECTIONS, null, false);

    @Override
    public boolean test(TrainRun run) {
        return matchesQuery(run)
                && matchesType(run)
                && matchesDirection(run)
                && matchesDate(run)
                && matchesSpecialSeats(run);
    }

    private boolean matchesQuery(TrainRun run) {
        if (query == null || query.isBlank()) {
            return true;
        }
        String q = query.trim().toLowerCase();
        return TrainRunView.number(run).contains(q)
                || TrainRunView.route(run).toLowerCase().contains(q);
    }

    private boolean matchesType(TrainRun run) {
        if (TrainRunView.EXPRESS.equals(type)) {
            return run.isExpress();
        }
        if (TrainRunView.PASSENGER.equals(type)) {
            return !run.isExpress();
        }
        return true;
    }

    private boolean matchesDirection(TrainRun run) {
        return direction == null
                || ALL_DIRECTIONS.equals(direction)
                || run.getRoute().direction().name().equals(direction);
    }

    private boolean matchesDate(TrainRun run) {
        return date == null || run.getDepartureTime().toLocalDate().equals(date);
    }

    private boolean matchesSpecialSeats(TrainRun run) {
        return !specialSeatsOnly || TrainRunView.hasSpecialSeats(run);
    }
}