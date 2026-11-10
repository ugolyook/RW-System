package com.sveta.rwcl.controller;

import com.sveta.route.Station;
import com.sveta.route.TrainRun;
import com.sveta.serialization.CoreState;
import com.sveta.serialization.CoreSerializer;
import com.sveta.tickets.*;
import com.sveta.tickets.calculate.BaseTicketPriceCalculator;
import com.sveta.train.Train;
import com.sveta.train.carriage.passenger.CoupeCarriage;
import com.sveta.view.TrainView;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TrainController {
    private static final int DEMO_TRAIN_NUMBER = 101;

    private final TicketSearchService ticketSearchService;
    private final Cashier cashier;
    private final TrainView view;
    private final CoreSerializer coreSerializer;

    private final List<Train> trains = new ArrayList<>();
    private final List<TrainRun> trainRuns = new ArrayList<>();

    public TrainController(
            TrainView view,
            CoreSerializer coreSerializer) {
        this.view = view;
        this.coreSerializer = coreSerializer;
        this.ticketSearchService = new TicketSearchService();
        this.cashier = new Cashier(new BaseTicketPriceCalculator());
    }

    public void run() {
        if (!loadState()) {
            return;
        }

        TrainRun run = trainRuns.stream()
                .filter(r -> r.getTrain().getTrainNumber() == DEMO_TRAIN_NUMBER)
                .min(java.util.Comparator.comparing(TrainRun::getDepartureTime))
                .orElse(null);

        if (run == null) {
            log.warn("Train run for train {} not found in storage.", DEMO_TRAIN_NUMBER);
            return;
        }

        view.showTrainCreated(run.getTrain());
        view.showTrainRunInfo(run);

        processBooking(run);
        saveState();
        log.info("TrainController workflow finished successfully.");
    }

    private void saveState() {
        try {
            coreSerializer.save(new CoreState(trainRuns, trains));
            log.info("State successfully saved ");
        } catch (Exception e) {
            log.error("Error saving state: {}", e.getMessage(), e);
        }
    }

    private boolean loadState() {
        try {
            CoreState loaded = coreSerializer.load();
            trains.addAll(loaded.trains());
            trainRuns.addAll(loaded.trainRuns());
            log.info("State loaded: {} trains, {} train runs",
                    loaded.trains().size(), loaded.trainRuns().size());
        } catch (Exception e) {
            log.error("Error loading state {}", e.getMessage(), e);
        }
        if (trainRuns.isEmpty()) {
            log.warn("Storage is empty. Run the init script first (make run_init_script).");
            return false;
        }
        return true;
    }

    private void processBooking(TrainRun run) {
        log.info("Initiating ticket booking process...");
        Station departureStation = findStationByCode(run, 2200001);
        Station arrivalStation = findStationByCode(run, 2200030);

        TicketSearchRequirement requirement = TicketSearchRequirement.builder()
                .departureStation(departureStation)
                .arrivalStation(arrivalStation)
                .trainRun(run)
                .route(run.getRoute())
                .dateTime(null)
                .departureDateTime(run.getDepartureTime())
                .train(run.getTrain())
                .carriageType(CoupeCarriage.class)
                .build();

        List<SearchResult> searchResults = ticketSearchService.searchSeats(List.of(run), requirement);
        view.showSearchResults(searchResults);

        if (searchResults.isEmpty()) {
            log.warn("No available seats found for the given requirements.");
            return;
        }

        SearchResult selectedSeat = searchResults.getFirst();
        Ticket ticket = cashier.issueTicket("Sveta", selectedSeat, departureStation, arrivalStation);

        view.showIssuedTicket(ticket);

        List<SearchResult> updatedResults = ticketSearchService.searchSeats(List.of(run), requirement);
        view.showRemainingSeats(updatedResults.size());
        log.info("Ticket booking process completed successfully.");
    }

    private Station findStationByCode(TrainRun run, int code) {
        return run.getRoute().stops().stream()
                .filter(s -> s.code() == code)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Station with code " + code + " not found in route!"));
    }
}