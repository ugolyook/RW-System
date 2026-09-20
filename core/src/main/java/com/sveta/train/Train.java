package com.sveta.train;

import com.sveta.locomotive.Locomotive;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.formatter.BaseTrainInfoFormatter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Train {
    private final List<Carriage> carriages = new ArrayList<>();
    private final int trainNumber;
    private final Locomotive locomotive;

    public Train(int trainNumber, Locomotive locomotive) {
        this.trainNumber = trainNumber;
        this.locomotive = locomotive;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public boolean checkLocomotiveExists() {
        return locomotive != null;
    }

    public void addCarriage(Carriage carriage) {
        carriages.add(carriage);
    }

    public Iterator<Carriage> getAllCarriage() {
        return carriages.iterator();
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
        BaseTrainInfoFormatter printer = new BaseTrainInfoFormatter();
        return printer.format(train);
    }

    public List<Carriage> getCarriages() {
        return carriages;
    }
}