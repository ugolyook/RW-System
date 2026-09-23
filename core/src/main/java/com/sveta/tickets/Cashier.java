package com.sveta.tickets;

import com.sveta.route.Station;

import java.math.BigDecimal;

public class Cashier {

    private final TicketPriceCalculator priceCalculator;

    public Cashier(TicketPriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    public Ticket issueTicket(String passengerName, SearchResult searchResult, Station departure, Station arrival) {
        BigDecimal price = priceCalculator.calculatePrice(searchResult, departure, arrival);

        if (searchResult.seat() != null) {
            searchResult.seat().setOccupied(true);
        }

        return new Ticket(
                passengerName,
                searchResult.trainRun(),
                departure,
                arrival,
                searchResult.carriage(),
                searchResult.seat(),
                price
        );
    }
}
