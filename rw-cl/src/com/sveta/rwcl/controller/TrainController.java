package com.sveta.rwcl.controller;

import com.sveta.TestDataFactory;
import com.sveta.factory.carriage.BaseCarriageFactory;
import com.sveta.factory.train.PassengerTrainFactory;
import com.sveta.route.Station;
import com.sveta.route.TrainRun;
import com.sveta.serialization.ObjectSerializer;
import com.sveta.tickets.*;
import com.sveta.tickets.calculate.BaseTicketPriceCalculator;
import com.sveta.train.Train;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.CoupeCarriage;
import com.sveta.view.TrainView;

import java.util.List;

public class TrainController {
    private final TicketSearchService ticketSearchService;
    private final Cashier cashier;
    private final TrainView view;
    private final ObjectSerializer serializer;

    public TrainController(TrainView view, ObjectSerializer serializer) {
        this.view = view;
        this.serializer = serializer;
        this.ticketSearchService = new TicketSearchService();
        this.cashier = new Cashier(new BaseTicketPriceCalculator());
    }

    public void run() {
        BaseCarriageFactory carriageFactory = TestDataFactory.createBaseCarriageFactory();
        PassengerTrainFactory trainFactory = TestDataFactory.createTrainFactory();

        List<Carriage> carriages = TestDataFactory.createCarriages(carriageFactory);
        Train train = trainFactory.createTrain(carriages);
        view.showTrainCreated(train);

        saveTrainState(train, "train_state.dat");

        TrainRun run = TestDataFactory.createTrainRun(train);
        view.showTrainRunInfo(run);

        processBooking(run);
    }

    private void saveTrainState(Train train, String filename) {
        try {
            serializer.save(train, filename);
            System.out.println("\n[Serializer] Train state successfully saved using: "
                    + serializer.getClass().getSimpleName());
        } catch (Exception e) {
            System.err.println("Error saving state: " + e.getMessage());
        }
    }

    private void processBooking(TrainRun run) {
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
            return;
        }

        SearchResult selectedSeat = searchResults.getFirst();
        Ticket ticket = cashier.issueTicket("Sveta", selectedSeat, departureStation, arrivalStation);

        view.showIssuedTicket(ticket);

        List<SearchResult> updatedResults = ticketSearchService.searchSeats(List.of(run), requirement);
        view.showRemainingSeats(updatedResults.size());
    }

    private Station findStationByCode(TrainRun run, int code) {
        return run.getRoute().stops().stream()
                .filter(s -> s.code() == code)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Station with code " + code + " not found in route!"));
    }
}