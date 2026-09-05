package com.sveta.train;

import com.sveta.carriage.Carriage;
import com.sveta.dto.TrainInfoFormater;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Train {
    private final List<Carriage> carriages = new ArrayList<>();
    private final int trainNumber;
    private Locomotive locomotive;

    public Train(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setLocomotive(Locomotive locomotive) {
        this.locomotive = locomotive;
    }

    public boolean checkLocomotiveExists() {
        return locomotive != null;
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

    public String printTrainInfo(Train train) {
        TrainInfoFormater printer = new TrainInfoFormater();
        return printer.format(train);
    }
}