package com.sveta.train.formatter;

import com.sveta.train.carriage.Carriage;
import com.sveta.train.Train;
import com.sveta.train.carriage.passenger.*;

import java.util.Iterator;

public class BaseTrainInfoFormatter implements TrainInfoFormatter {
    @Override
    public String format(Train train) {
        StringBuilder sb = new StringBuilder();

        sb.append("Train №").append(train.getTrainNumber()).append("\n");
        sb.append("Carriages: ").append(train.getCarriageCount()).append("\n");
        sb.append("Passenger seats: ").append(train.getTotalPassengerCap()).append("\n");
        sb.append("Total weight: ").append(train.getTotalWeight()).append(" т\n");
        sb.append("--- List of railcars ---\n");

        Iterator<Carriage> iterator = train.getAllCarriage();
        int index = 1;

        while (iterator.hasNext()) {
            Carriage carriage = iterator.next();
            sb.append(" [#").append(index++).append("] ")
                    .append(formatCarriage(carriage))
                    .append("\n");
        }

        return sb.toString();
    }

    private String formatCarriage(Carriage carriage) {
        if (carriage instanceof CoupeCarriage coupeCarriage) {
            return String.format("Coupe Carriage | Coupes: %d | Free seats: %d/%d",
                    coupeCarriage.getCoupeLimit(),
                    countAvailableSeats(coupeCarriage),
                    coupeCarriage.getPassengerCapacity());
        }

        if (carriage instanceof EconomyCarriage economyCarriage) {
            return String.format("Economy Carriage (Platskart) | Free seats: %d/%d",
                    countAvailableSeats(economyCarriage),
                    economyCarriage.getPassengerCapacity());
        }

        if (carriage instanceof SeatedCarriage seatedCarriage) {
            return String.format("Seated Carriage | Free seats: %d/%d",
                    countAvailableSeats(seatedCarriage),
                    seatedCarriage.getPassengerCapacity());
        }

        if (carriage instanceof DiningCarriage diningCarriage) {
            return String.format("Dining Carriage | Seats limit: %d | Speciality: %s",
                    diningCarriage.getPassengerCapacity(),
                    diningCarriage.food != null ? diningCarriage.food : "N/A");
        }

        return carriage.toString();
    }

    private long countAvailableSeats(PassengerCarriage carriage) {
        return carriage.getAllSeats().stream()
                .filter(seat -> !seat.isOccupied())
                .count();
    }
}