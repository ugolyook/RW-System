package com.sveta.train;

import com.sveta.carriage.Carriage;
import com.sveta.dto.TrainInfoDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Train {
    private Locomotive locomotive;
    private final List<Carriage> carriages = new ArrayList<>();
    private final int trainNumber;

    public Train(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public void setLocomotive(Locomotive locomotive) {
        this.locomotive = locomotive;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public void addCarriage(Carriage carriage) {
        carriages.add(carriage);
    }

    public void removeCarriage(int id) {
        carriages.remove(id);
    }

    public Iterator<Carriage> getAllCarriage() {
        return carriages.iterator();
    }

    public void insertCarriage(int id, Carriage carriage) {
        carriages.add(id, carriage);
    }

    public int getTotalPassengerCap() {
        return carriages.stream()
                .mapToInt(Carriage::getPassengerCapacity)
                .sum();
    }

    public double getTotalWeight() {
        return carriages.stream()
                .mapToInt(Carriage::getKgWeight)
                .sum();
    }

    public int getCarriageCount() {
        return carriages.size();
    }

    public String printTrainInfo() {
        TrainInfoDTO trainInfoDTO = new TrainInfoDTO(
                trainNumber,
                getCarriageCount(),
                getTotalPassengerCap(),
                getTotalWeight(),
                carriages);
        return trainInfoDTO.toString();
    }
}