package com.sveta.tickets;

import com.sveta.exeptions.CashierExceptions;
import com.sveta.route.Station;
import com.sveta.route.TrainRun;
import com.sveta.tickets.calculate.TicketPriceCalculator;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class Cashier {
    private final TicketPriceCalculator priceCalculator;

    public Ticket issueTicket(
            String passengerName,
            SearchResult searchResult,
            Station departure,
            Station arrival
    ) {
        TrainRun trainRun = searchResult.trainRun();

        if (searchResult.seat() != null && trainRun.isSeatOccupied(searchResult.carriage(), searchResult.seat())) {
            throw new CashierExceptions.OccupiedSeatException();
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