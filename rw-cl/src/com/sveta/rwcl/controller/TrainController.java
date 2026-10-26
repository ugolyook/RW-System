package com.sveta.rwcl.controller;

import com.sveta.TestDataFactory;
import com.sveta.factory.carriage.BaseCarriageFactory;
import com.sveta.factory.train.PassengerTrainFactory;
import com.sveta.route.Station;
import com.sveta.route.TrainRun;
import com.sveta.serialization.impl.config.TrainRunStorageService;
import com.sveta.serialization.impl.config.StorageConfigManager;
import com.sveta.tickets.*;
import com.sveta.tickets.calculate.BaseTicketPriceCalculator;
import com.sveta.train.Train;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.CoupeCarriage;
import com.sveta.view.TrainView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TrainController {
    private static final Logger log = LoggerFactory.getLogger(TrainController.class);
    private static final String STATE_FILE_PATH = System.getProperty("user.home") + "/.rw-system/train_run_state.dat";

    private final TicketSearchService ticketSearchService;
    private final Cashier cashier;
    private final TrainView view;
    private final TrainRunStorageService storageService;

    public TrainController(TrainView view, TrainRunStorageService storageService) {
        this.view = view;
        this.storageService = storageService;
        this.ticketSearchService = new TicketSearchService();
        this.cashier = new Cashier(new BaseTicketPriceCalculator());
    }

    public void run() {
        BaseCarriageFactory carriageFactory = TestDataFactory.createBaseCarriageFactory();
        PassengerTrainFactory trainFactory = TestDataFactory.createTrainFactory();

        List<Carriage> carriages = TestDataFactory.createCarriages(carriageFactory);
        Train train = trainFactory.createTrain(carriages);
        view.showTrainCreated(train);

        TrainRun run = TestDataFactory.createTrainRun(train);
        view.showTrainRunInfo(run);

        String filePath = StorageConfigManager.resolveFilePath(STATE_FILE_PATH);
        saveTrainRunState(run, filePath);

        TrainRun loadedRun = loadTrainRunState(STATE_FILE_PATH);
        if (loadedRun != null) {
            log.info("TrainRun successfully loaded and mapped back to Domain model!");
        }

        processBooking(run);
        log.info("TrainController workflow finished successfully.");
    }

    private void saveTrainRunState(TrainRun run, String filename) {
        try {
            storageService.save(run, filename);
            log.info("TrainRun state successfully saved to '{}'", filename);
        } catch (Exception e) {
            log.error("Error saving train run state to '{}': {}", filename, e.getMessage(), e);
        }
    }

    private TrainRun loadTrainRunState(String filename) {
        try {
            log.info("Loading TrainRun from '{}'...", filename);
            return storageService.load(filename);
        } catch (Exception e) {
            log.error("Error loading train run state from '{}': {}", filename, e.getMessage(), e);
            return null;
        }
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