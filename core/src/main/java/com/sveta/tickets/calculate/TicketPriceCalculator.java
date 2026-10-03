package com.sveta.tickets.calculate;

import com.sveta.route.Station;
import com.sveta.tickets.SearchResult;

import java.math.BigDecimal;

public interface TicketPriceCalculator {
    BigDecimal calculatePrice(SearchResult searchResult, Station departure, Station arrival);
}