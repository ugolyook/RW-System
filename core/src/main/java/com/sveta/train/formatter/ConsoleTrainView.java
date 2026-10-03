package com.sveta.train.formatter;

import com.sveta.route.TrainRun;
import com.sveta.tickets.SearchResult;
import com.sveta.tickets.Ticket;
import com.sveta.train.Train;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.PassengerCarriage;

import java.util.List;

public class ConsoleTrainView implements TrainView{
    private final TrainInfoFormatter trainInfoFormatter;

    public ConsoleTrainView(TrainInfoFormatter trainInfoFormatter) {
        this.trainInfoFormatter = trainInfoFormatter;
    }
    @Override
    public void showTrainCreated(Train train) {
        System.out.println("We build a first train!");
        System.out.println(trainInfoFormatter.format(train));
    }

    @Override
    public void showTrainRunInfo(TrainRun run) {
        System.out.println("Route: " + run.getRoute().getDirection());
        System.out.println("Train stops:");
        for (var station : run.getRoute().getStops()) {
            System.out.println(" - " + station);
        }
        System.out.println("Departure: " + run.getDepartureTime());
        System.out.println("\n--- Seats Availability ---");
        var iterator = run.getTrain().getAllCarriage();
        int index = 1;

        while (iterator.hasNext()) {
            Carriage carriage = iterator.next();
            if (carriage instanceof PassengerCarriage passengerCarriage) {
                int totalCapacity = passengerCarriage.getPassengerCapacity();
                long freeSeats = passengerCarriage.getAllSeats().stream()
                        .filter(seat -> !run.isSeatOccupied(passengerCarriage, seat))
                        .count();
                long occupiedSeats = totalCapacity - freeSeats;

                System.out.printf(" [#%d] %s | Free: %d | Occupied: %d | Total: %d%n",
                        index,
                        passengerCarriage.getClass().getSimpleName(),
                        freeSeats,
                        occupiedSeats,
                        totalCapacity);
            } else {
                System.out.printf(" [#%d] %s%n", index, carriage.getClass().getSimpleName());
            }
            index++;
        }
    }

    @Override
    public void showSearchResults(List<SearchResult> searchResults) {
        System.out.println("\n=== Ticket search results (" + searchResults.size() + " found) ===");
        if (searchResults.isEmpty()) {
            System.out.println("No available seats found.");
            return;
        }
        searchResults.stream().limit(5).forEach(System.out::println);
    }

    @Override
    public void showIssuedTicket(Ticket ticket) {
        System.out.println("\n=== Issued Ticket ===");
        System.out.println(ticket);
    }

    @Override
    public void showRemainingSeats(int count) {
        System.out.println("\n=== Free seats after booking: " + count + " ===");
    }
}