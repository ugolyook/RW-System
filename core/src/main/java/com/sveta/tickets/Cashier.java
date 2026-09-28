package com.sveta.tickets;

import com.sveta.route.Station;
import com.sveta.route.TrainRun;
import com.sveta.tickets.calculate.TicketPriceCalculator;

import java.math.BigDecimal;

public class Cashier {

    private final TicketPriceCalculator priceCalculator;

    public Cashier(TicketPriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    public Ticket issueTicket(
            String passengerName,
            SearchResult searchResult,
            Station departure,
            Station arrival
    ) {
        TrainRun trainRun = searchResult.trainRun();

        if (searchResult.seat() != null && trainRun.isSeatOccupied(searchResult.carriage(), searchResult.seat())) {
            throw new IllegalStateException("Place was booked...");
        }
        BigDecimal price = priceCalculator.calculatePrice(searchResult, departure, arrival);

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

        return ticket;
    }
}