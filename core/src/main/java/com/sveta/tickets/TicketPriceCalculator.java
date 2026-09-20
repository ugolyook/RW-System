package com.sveta.tickets;

import com.sveta.route.Station;
import com.sveta.train.carriage.passenger.CoupeCarriage;
import com.sveta.train.carriage.passenger.EconomyCarriage;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TicketPriceCalculator {
    private static final BigDecimal BASE_STATION_FEE = new BigDecimal("12.50");

    public BigDecimal calculatePrice(SearchResult searchResult, Station departure, Station arrival) {
        var trainRun = searchResult.trainRun();
        var carriage = searchResult.carriage();
        var seat = searchResult.seat();

        int distance = calculateDistanceInStops(trainRun.route(), departure, arrival);
        BigDecimal price = BASE_STATION_FEE.multiply(BigDecimal.valueOf(distance));

        double carriageMultiplier = switch (carriage) {
            case CoupeCarriage c -> 1.8;
            case EconomyCarriage e -> 1.0;
            default -> 1.2;
        };
        price = price.multiply(BigDecimal.valueOf(carriageMultiplier));

        if (trainRun.isExpress()) {
            price = price.multiply(new BigDecimal("1.25"));
        }

            return price.setScale(2, RoundingMode.HALF_UP);
    }

    private int calculateDistanceInStops(com.sveta.route.Route route, Station departure, Station arrival) {
        if (departure == null || arrival == null || route == null) {
            return 1;
        }
        var stops = route.getStops();
        int depIndex = stops.indexOf(departure);
        int arrIndex = stops.indexOf(arrival);

        if (depIndex != -1 && arrIndex != -1 && arrIndex > depIndex) {
            return arrIndex - depIndex;
        }
        return 1;
    }
}
