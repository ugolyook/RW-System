package com.sveta.javafx;

import com.sveta.route.TrainRun;
import com.sveta.train.carriage.passenger.PassengerCarriage;
import com.sveta.train.carriage.passenger.models.SeatType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public final class TrainRunView {
    public static final String PASSENGER = "Passenger";
    public static final String EXPRESS = "Express";
    public static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm");

    private static final String EMPTY = "—";

    private TrainRunView() {
    }

    public static String number(TrainRun run) {
        return String.valueOf(run.getTrain().getTrainNumber());
    }

    public static String route(TrainRun run) {
        var stops = run.getRoute().stops();
        if (stops.isEmpty()) {
            return "";
        }
        return stops.getFirst().stationName() + " → " + stops.getLast().stationName();
    }

    public static String typeLabel(TrainRun run) {
        return run.isExpress() ? EXPRESS : PASSENGER;
    }

    public static String departure(TrainRun run) {
        return run.getDepartureTime().format(TIME);
    }

    public static int freeSeats(TrainRun run) {
        return Math.max(0, run.getTrain().getTotalPassengerCap() - run.getTickets().size());
    }

    public static boolean hasSpecialSeats(TrainRun run) {
        return run.getTrain().getCarriages().stream()
                .filter(PassengerCarriage.class::isInstance)
                .map(PassengerCarriage.class::cast)
                .flatMap(carriage -> carriage.getAllSeats().stream())
                .anyMatch(seat -> seat.type() == SeatType.BICYCLE
                        || seat.type() == SeatType.PET);
    }

    public static String selectedText(TrainRun run) {
        if (run == null) {
            return EMPTY;
        }
        return "№ " + number(run) + "\n"
                + route(run) + "\n"
                + departure(run) + " · " + typeLabel(run);
    }

    public static int totalFreeSeats(List<TrainRun> runs) {
        return runs.stream().mapToInt(TrainRunView::freeSeats).sum();
    }

    public static String nextDeparture(List<TrainRun> runs) {
        return runs.stream()
                .map(TrainRun::getDepartureTime)
                .min(Comparator.<LocalDateTime>naturalOrder())
                .map(time -> time.format(TIME))
                .orElse(EMPTY);
    }
}