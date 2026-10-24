package com.sveta.tickets;

import com.sveta.exeptions.CashierExceptions;
import com.sveta.route.Station;
import com.sveta.route.TrainRun;
import com.sveta.tickets.calculate.TicketPriceCalculator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class Cashier {
    private static final Logger log = LoggerFactory.getLogger(Cashier.class);
    private final TicketPriceCalculator priceCalculator;

    public Ticket issueTicket(
            String passengerName,
            SearchResult searchResult,
            Station departure,
            Station arrival
    ) {
        log.info("Cashier attempting to issue ticket for passenger '{}' on train run from '{}' to '{}'",
                passengerName,
                departure != null ? departure.stationName() : "START",
                arrival != null ? arrival.stationName() : "END");

        TrainRun trainRun = searchResult.trainRun();

        if (searchResult.seat() != null && trainRun.isSeatOccupied(searchResult.carriage(), searchResult.seat())) {
            throw new CashierExceptions.OccupiedSeatException();
        }
        BigDecimal price = priceCalculator.calculatePrice(searchResult, departure, arrival);
        log.debug("Calculated ticket price: {} for passenger '{}'", price, passengerName);

        Ticket ticket = new Ticket(
                passengerName,
                trainRun,
                departure,
                arrival,
                searchResult.carriage(),
                searchResult.seat(),
                price
        );

        trainRun.addTicket(ticket);
        log.info("Ticket successfully issued for passenger '{}'. Total price: {} BYN", passengerName, price);

        return ticket;
    }
}