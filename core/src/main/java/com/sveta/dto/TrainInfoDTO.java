package com.sveta.dto;

import com.sveta.carriage.Carriage;

import java.util.List;

public class TrainInfoDTO {
    private final int trainNumber;
    private final int carriageCount;
    private final int totalPassengerCapacity;
    private final double totalWeight;
    private final List<Carriage> carriageDescriptions;

    public TrainInfoDTO(int trainNumber, int carriageCount, int totalPassengerCapacity,
                        double totalWeight, List<Carriage> carriageDescriptions) {
        this.trainNumber = trainNumber;
        this.carriageCount = carriageCount;
        this.totalPassengerCapacity = totalPassengerCapacity;
        this.totalWeight = totalWeight;
        this.carriageDescriptions = carriageDescriptions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Train №").append(trainNumber).append("\n");
        sb.append("Carriages: ").append(carriageCount).append("\n");
        sb.append("Passenger seats: ").append(totalPassengerCapacity).append("\n");
        sb.append("Total weight: ").append(totalWeight).append(" т\n");
        sb.append("--- List of railcars ---\n");
        for (Carriage desc : carriageDescriptions) {
            sb.append(desc).append("\n");
        }
        return sb.toString();
    }
}
