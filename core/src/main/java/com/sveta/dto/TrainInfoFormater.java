package com.sveta.dto;

import com.sveta.carriage.Carriage;
import com.sveta.train.Train;

import java.util.Iterator;

public class TrainInfoFormater implements TrainFormatInfo {
    @Override
    public String format(Train train) {
        StringBuilder sb = new StringBuilder();

        sb.append("Train №").append(train.getTrainNumber()).append("\n");
        sb.append("Carriages: ").append(train.getCarriageCount()).append("\n");
        sb.append("Passenger seats: ").append(train.getTotalPassengerCap()).append("\n");
        sb.append("Total weight: ").append(train.getTotalWeight()).append(" т\n");
        sb.append("--- List of railcars ---\n");

        Iterator<Carriage> iterator = train.getAllCarriage();
        while (iterator.hasNext()) {
            sb.append(iterator.next()).append("\n");
        }
        return sb.toString();
    }
}
