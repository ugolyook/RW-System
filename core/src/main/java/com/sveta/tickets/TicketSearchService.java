package com.sveta.tickets;

import com.sveta.route.Station;
import com.sveta.route.TrainRun;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.PassengerCarriage;
import com.sveta.train.carriage.passenger.models.Seat;

import java.util.ArrayList;
import java.util.List;

public class TicketSearchService {
    public List<SearchResult> searchSeats(List<TrainRun> trainRuns, TicketSearchRequirement requirement) {
        List<SearchResult> results = new ArrayList<>();

        if (trainRuns == null || requirement == null) {
            return results;
        }

        for (TrainRun run : trainRuns) {
            if (requirement.getTrainRun() != null && !requirement.getTrainRun().equals(run)) {
                continue;
            }

            if (requirement.getTrain() != null && !requirement.getTrain().equals(run.train())) {
                continue;
            }

            if (requirement.getRoute() != null && !requirement.getRoute().equals(run.route())) {
                continue;
            }

            if (!matchesStations(run, requirement.getDepartureStation(), requirement.getArrivalStation())) {
                continue;
            }

            if (!matchesDepartureTime(run, requirement)) {
                continue;
            }

            for (Carriage carriage : run.train().getCarriages()) {
                if (requirement.getCarriageType() != null && !requirement.getCarriageType().isInstance(carriage)) {
                    continue;
                }

                if (carriage instanceof PassengerCarriage passengerCarriage) {
                    for (Seat seat : passengerCarriage.getAllSeats()) {
                        if (!seat.isOccupied()) {
                            results.add(new SearchResult(run, carriage, seat));
                        }
                    }
                }
            }
        }

        return results;
    }

    private boolean matchesStations(TrainRun run, Station departure, Station arrival) {
        if (departure == null || arrival == null) {
            return true;
        }

        List<Station> stops = run.route().getStops();
        int depIndex = stops.indexOf(departure);
        int arrIndex = stops.indexOf(arrival);

        return depIndex != -1 && arrIndex != -1 && depIndex < arrIndex;
    }

    private boolean matchesDepartureTime(TrainRun run, TicketSearchRequirement requirement) {
        if (requirement.getDepartureDateTime() != null) {
            return run.departureTime().equals(requirement.getDepartureDateTime());
        }
        if (requirement.getDepartureDate() != null) {
            return run.departureTime().toLocalDate().equals(requirement.getDepartureDate());
        }
        return true;
    }
}