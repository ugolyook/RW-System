package com.sveta.tickets;

import com.sveta.route.Station;
import com.sveta.route.TrainRun;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.PassengerCarriage;
import com.sveta.train.carriage.passenger.SeatedCarriage;
import com.sveta.train.carriage.passenger.models.Seat;
import com.sveta.train.carriage.passenger.models.SeatType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketSearchService {
    public List<SearchResult> searchSeats(List<TrainRun> trainRuns, TicketSearchRequirement requirement) {
        if (trainRuns == null || requirement == null) {
            return List.of();
        }

        List<SearchResult> results = new ArrayList<>();
        for (TrainRun run : trainRuns) {
            if (matchesRun(run, requirement)) {
                collectSeats(run, requirement, results);
            }
        }
        return results;
    }

    private boolean matchesRun(TrainRun run, TicketSearchRequirement requirement) {
        return matches(requirement.getTrainRun(), run)
                && matches(requirement.getTrain(), run.getTrain())
                && matches(requirement.getRoute(), run.getRoute())
                && matchesStations(run, requirement.getDepartureStation(), requirement.getArrivalStation())
                && matchesDepartureTime(run, requirement);
    }

    private static <T> boolean matches(T required, T actual) {
        return required == null || required.equals(actual);
    }

    private boolean matchesStations(TrainRun run, Station departure, Station arrival) {
        if (departure == null || arrival == null) {
            return true;
        }

        List<Station> stops = run.getRoute().getStops();
        int depIndex = stops.indexOf(departure);
        int arrIndex = stops.indexOf(arrival);

        return depIndex != -1 && arrIndex != -1 && depIndex < arrIndex;
    }

    private void collectSeats(TrainRun run, TicketSearchRequirement requirement,
                              List<SearchResult> results) {
        for (Carriage carriage : run.getTrain().getCarriages()) {
            if (matchesCarriage(carriage, requirement)) {
                collectSeatsInCarriage(run, carriage, requirement, results);
            }
        }
    }

    private boolean matchesCarriage(Carriage carriage, TicketSearchRequirement requirement) {
        if (requirement.getCarriageType() != null
                && !requirement.getCarriageType().isInstance(carriage)) {
            return false;
        }
        if (!requirement.isBicycleRequired()) {
            return true;
        }
        return carriage instanceof SeatedCarriage sc && sc.isBicycleSpots();
    }

    private void collectSeatsInCarriage(TrainRun run, Carriage carriage,
                                        TicketSearchRequirement requirement,
                                        List<SearchResult> results) {
        if (!(carriage instanceof PassengerCarriage passenger)) {
            return;
        }
        for (Seat seat : passenger.getAllSeats()) {
            if (!run.isSeatOccupied(carriage, seat) && matchesBicycle(seat, requirement)) {
                results.add(new SearchResult(run, carriage, seat));
            }
        }
    }

    private boolean matchesBicycle(Seat seat, TicketSearchRequirement requirement) {
        return !requirement.isBicycleRequired() || seat.getType() == SeatType.BICYCLE;
    }

    private boolean matchesDepartureTime(TrainRun run, TicketSearchRequirement requirement) {
        LocalDateTime depTime = run.getDepartureTime();
        LocalDateTime from = requirement.getDateTime();
        LocalDateTime to = requirement.getDepartureDateTime();

        if (from != null && depTime.isBefore(from)) {
            return false;
        }
        if (to != null && depTime.isAfter(to)) {
            return false;
        }
        if (from == null && to == null && requirement.getDepartureDate() != null) {
            return depTime.toLocalDate().equals(requirement.getDepartureDate());
        }
        return true;
    }
}