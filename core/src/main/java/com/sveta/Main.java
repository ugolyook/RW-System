package com.sveta;

import com.sveta.route.Directions;
import com.sveta.route.Route;
import com.sveta.route.Station;
import com.sveta.route.TrainRun;
import com.sveta.tickets.*;
import com.sveta.train.Train;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.CoupeCarriage;
import com.sveta.train.carriage.passenger.models.Food;
import com.sveta.factory.carriage.BaseCarriageFactory;
import com.sveta.factory.carriage.CarriageRequirement;
import com.sveta.factory.carriage.coupe.CoupeCarriageFactory;
import com.sveta.factory.carriage.coupe.CoupeCarriageRequirement;
import com.sveta.factory.carriage.coupe.CoupeRequirement;
import com.sveta.factory.carriage.dining.DiningCarriageFactory;
import com.sveta.factory.carriage.dining.DiningCarriageRequirement;
import com.sveta.factory.carriage.economy.EconomyCarriageFactory;
import com.sveta.factory.carriage.economy.EconomyCarriageRequirement;
import com.sveta.factory.carriage.seated.SeatedCarriageFactory;
import com.sveta.factory.carriage.seated.SeatedCarriageRequirement;
import com.sveta.factory.locomotive.LocomotiveFactory;
import com.sveta.factory.train.PassengerTrainFactory;
import com.sveta.train.formatter.BaseTrainInfoFormatter;
import com.sveta.train.formatter.ConsoleTrainView;
import com.sveta.train.formatter.TrainView;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    private final static int DEFAULT_CARRIAGE_LIMIT_PER_TRAIN = 18;

    private final PassengerTrainFactory passengerTrainFactory;
    private final BaseCarriageFactory baseCarriageFactory;

    private final TicketSearchService ticketSearchService;
    private final Cashier cashier;

    private final TrainView view;

    static void main(String[] args) {
        TrainView view = new ConsoleTrainView(new BaseTrainInfoFormatter());
        var main = new Main(view);
        main.start();
    }

    public Main(TrainView view) {
        this.view = view;
        this.passengerTrainFactory = configureTrainFactory();
        this.baseCarriageFactory = configureBaseCarriageFactory();

        this.ticketSearchService = new TicketSearchService();
        TicketPriceCalculator priceCalculator = new TicketPriceCalculator();
        this.cashier = new Cashier(priceCalculator);
    }

    private void start() {
        var carriages = createCarriages();
        var train = passengerTrainFactory.createTrain(carriages);

        view.showTrainCreated(train);

        TrainRun run = createTrainRun(train);
        processTicketSearchAndBooking(run);
    }

    private void processTicketSearchAndBooking(TrainRun run) {
        Station minsk = run.route().getStops().get(0);
        Station mogilev = run.route().getStops().get(2);

        TicketSearchRequirement requirement = new TicketSearchRequirement(
                minsk,
                mogilev,
                run,
                run.route(),
                null,
                run.departureTime(),
                run.train(),
                CoupeCarriage.class
        );

        List<SearchResult> searchResults = ticketSearchService.searchSeats(List.of(run), requirement);
        view.showSearchResults(searchResults);

        if (searchResults.isEmpty()) {
            return;
        }

        searchResults.stream().limit(5).forEach(System.out::println);

        SearchResult selectedResult = searchResults.get(0);
        Ticket ticket = cashier.issueTicket("Sveta", selectedResult, minsk, mogilev);

        view.showIssuedTicket(ticket);

        List<SearchResult> updatedResults = ticketSearchService.searchSeats(List.of(run), requirement);
        view.showRemainingSeats(updatedResults.size());
    }

    private TrainRun createTrainRun(Train train) {
        Station minsk = new Station("Minsk", 2200001);
        Station minskPassenger = new Station("Minsk-Passenger", 2200020);
        Station mogilev = new Station("Mogilev", 2200030);
        Station mogilevCentral = new Station("Mogilev Central", 2200060);

        List<Station> stops = List.of(minsk, minskPassenger, mogilev, mogilevCentral);
        Route route = new Route(stops, Directions.FORWARD);

        LocalDateTime departure = LocalDateTime.of(2026, 9, 20, 14, 30);

        TrainRun run = new TrainRun(train, route, departure, true);
        view.showTrainRunInfo(run);

        return new TrainRun(train, route, departure, true);
    }

    private List<Carriage> createCarriages() {
        var coupeReq1 = new CoupeRequirement(111, 62, false, false);
        var coupeReq2 = new CoupeRequirement(11, 62, true, false);
        var coupeReq3 = new CoupeRequirement(11, 62, false, false);

        CarriageRequirement coupeCarriageReq1 = new CoupeCarriageRequirement(
                List.of(coupeReq1, coupeReq1, coupeReq1, coupeReq1, coupeReq2, coupeReq2, coupeReq2, coupeReq3),
                50000
        );

        CarriageRequirement coupeCarriageReq2 = new CoupeCarriageRequirement(
                List.of(coupeReq1, coupeReq1, coupeReq2, coupeReq3, coupeReq3, coupeReq3, coupeReq3),
                50000
        );

        var allRequirement = getCarriageRequirements(coupeCarriageReq1, coupeCarriageReq2);

        return baseCarriageFactory.createAll(allRequirement);
    }

    private static List<CarriageRequirement> getCarriageRequirements(CarriageRequirement coupeCarriageReq1, CarriageRequirement coupeCarriageReq2) {
        CarriageRequirement seatedCarriageReq = new SeatedCarriageRequirement(
                48,
                2,
                10000
        );

        CarriageRequirement diningCarriageReq = new DiningCarriageRequirement(
                32,
                45000,
                Food.BORSCH,
                true,
                false
        );

        CarriageRequirement economyCarriageReq = new EconomyCarriageRequirement(
                54,
                48000,
                true
        );

        return List.of(
                coupeCarriageReq1,
                coupeCarriageReq2,
                seatedCarriageReq,
                diningCarriageReq,
                economyCarriageReq
        );
    }

    private BaseCarriageFactory configureBaseCarriageFactory() {
        var coupeFactory = new CoupeCarriageFactory();
        var seatedFactory = new SeatedCarriageFactory();
        var diningFactory = new DiningCarriageFactory();
        var economyFactory = new EconomyCarriageFactory();

        var allCarriageFactories = List.of(
                coupeFactory,
                seatedFactory,
                diningFactory,
                economyFactory
        );

        return new BaseCarriageFactory(allCarriageFactories);
    }

    private PassengerTrainFactory configureTrainFactory() {
        var locomotiveFactory = new LocomotiveFactory();
        return new PassengerTrainFactory(DEFAULT_CARRIAGE_LIMIT_PER_TRAIN, locomotiveFactory);
    }
}