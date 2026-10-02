package com.sveta.train.formatter;

import com.sveta.route.TrainRun;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.Train;
import com.sveta.train.carriage.passenger.*;
import com.sveta.train.carriage.passenger.models.Food;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
            return String.format("Coupe Carriage | Coupes: %d | Total seats: %d",
                    coupeCarriage.getCoupeLimit(),
                    coupeCarriage.getNumberOfPlaces());
        }

        if (carriage instanceof EconomyCarriage economyCarriage) {
            return String.format("Economy Carriage (Platskart) | Total seats: %d",
                    economyCarriage.getPassengerCapacity());
        }

        if (carriage instanceof SeatedCarriage seatedCarriage) {
            return String.format("Seated Carriage | Total seats: %d",
                    seatedCarriage.getPassengerCapacity());
        }

        if (carriage instanceof DiningCarriage diningCarriage) {
            String foodInfo = formatFood(diningCarriage.getFood());
            return String.format("Dining Carriage | Seats limit: %d | Speciality: %s",
                    diningCarriage.getPassengerCapacity(),
                    foodInfo);
        }

        return carriage.toString();
    }

    private String formatFood(List<Food> foods) {
        if (foods == null || foods.isEmpty()) {
            return "No menu available";
        }

        return foods.stream()
                .map(Food::toString)
                .collect(Collectors.joining(", "));
    }
}