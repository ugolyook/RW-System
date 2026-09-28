package com.sveta.train.formatter;

import com.sveta.route.TrainRun;
import com.sveta.train.Train;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.PassengerCarriage;

import java.util.Iterator;

public class FreePlaceInfoFormatter implements TrainInfoFormatter {

    private final TrainInfoFormatter baseFormatter;

    public FreePlaceInfoFormatter() {
        this(new BaseTrainInfoFormatter());
    }

    public FreePlaceInfoFormatter(TrainInfoFormatter baseFormatter) {
        this.baseFormatter = baseFormatter;
    }

    @Override
    public String format(Train train) {
        return baseFormatter.format(train);
    }

    public String format(TrainRun trainRun) {
        if (trainRun == null || trainRun.getTrain() == null) {
            return "No train run information available.";
        }

        Train train = trainRun.getTrain();
        StringBuilder sb = new StringBuilder();

        sb.append("=== TRAIN RUN INFO ===\n");
        sb.append("Train №").append(train.getTrainNumber()).append("\n");
        sb.append("Route: ").append(trainRun.getRoute()).append("\n");
        sb.append("Departure: ").append(trainRun.getDepartureTime()).append("\n");
        sb.append("Carriages: ").append(train.getCarriageCount()).append("\n");
        sb.append("Passenger seats: ").append(train.getTotalPassengerCap()).append("\n");
        sb.append("Total weight: ").append(train.getTotalWeight()).append(" т\n");
        sb.append("--- List of railcars ---\n");

        Iterator<Carriage> iterator = train.getAllCarriage();
        int index = 1;

        while (iterator.hasNext()) {
            Carriage carriage = iterator.next();

            sb.append(" [#").append(index++).append("] ")
                    .append(formatCarriageWithSeats(trainRun, carriage))
                    .append("\n");
        }

        return sb.toString();
    }

    private String formatCarriageWithSeats(TrainRun trainRun, Carriage carriage) {
        if (carriage instanceof PassengerCarriage passengerCarriage) {
            long freeSeats = countAvailableSeats(trainRun, passengerCarriage);
            return String.format("%s | Free seats: %d/%d",
                    passengerCarriage.getClass().getSimpleName(),
                    freeSeats,
                    passengerCarriage.getPassengerCapacity());
        }

        return carriage.toString();
    }

    private long countAvailableSeats(TrainRun trainRun, PassengerCarriage carriage) {
        return carriage.getAllSeats().stream()
                .filter(seat -> !trainRun.isSeatOccupied(carriage, seat))
                .count();
    }
}