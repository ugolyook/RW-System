package com.sveta;

import com.sveta.factory.carriage.BaseCarriageFactory;
import com.sveta.factory.train.PassengerTrainFactory;
import com.sveta.route.Station;
import com.sveta.route.TrainRun;
import com.sveta.tickets.*;
import com.sveta.tickets.calculate.BaseTicketPriceCalculator;
import com.sveta.train.Train;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.CoupeCarriage;
import com.sveta.train.formatter.BaseTrainInfoFormatter;
import com.sveta.train.formatter.ConsoleTrainView;
import com.sveta.train.formatter.TrainView;

import java.util.List;

public class Main {

    private final TicketSearchService ticketSearchService;
    private final Cashier cashier;
    private final TrainView view;

     static void main(String[] args) {
        TrainView view = new ConsoleTrainView(new BaseTrainInfoFormatter());
        Main app = new Main(view);
        app.run();
    }

    public Main(TrainView view) {
        this.view = view;
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

        processBooking(run);
    }

    private void processBooking(TrainRun run) {
        Station departureStation = findStationByCode(run, 2200001);
        Station arrivalStation = findStationByCode(run, 2200030);

        TicketSearchRequirement requirement = new TicketSearchRequirement(
                departureStation,
                arrivalStation,
                run,
                run.getRoute(),
                null,
                run.getDepartureTime(),
                run.getTrain(),
                CoupeCarriage.class
        );

        List<SearchResult> searchResults = ticketSearchService.searchSeats(List.of(run), requirement);
        view.showSearchResults(searchResults);

        if (searchResults.isEmpty()) {
            return;
        }

        SearchResult selectedSeat = searchResults.get(0);
        Ticket ticket = cashier.issueTicket("Sveta", selectedSeat, departureStation, arrivalStation);

        view.showIssuedTicket(ticket);

        List<SearchResult> updatedResults = ticketSearchService.searchSeats(List.of(run), requirement);
        view.showRemainingSeats(updatedResults.size());
    }

    private Station findStationByCode(TrainRun run, int code) {
        return run.getRoute().getStops().stream()
                .filter(s -> s.getCode() == code)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Station with code " + code + " not found in route!"));
    }
}