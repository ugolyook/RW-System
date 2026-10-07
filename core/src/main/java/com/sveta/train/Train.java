package com.sveta.train;

import com.sveta.locomotive.Locomotive;
import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.passenger.DiningCarriage;
import com.sveta.train.carriage.passenger.models.Food;
import com.sveta.train.formatter.BaseTrainInfoFormatter;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Getter
public abstract class Train {
    private final List<Carriage> carriages = new ArrayList<>();
    private final int trainNumber;
    private final Locomotive locomotive;

    public Train(int trainNumber, Locomotive locomotive) {
        this.trainNumber = trainNumber;
        this.locomotive = locomotive;
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

    public Optional<DiningCarriage> findDiningCarriage() {
        return carriages.stream()
                .filter(DiningCarriage.class::isInstance)
                .map(DiningCarriage.class::cast)
                .findFirst();
    }

    public double orderFood(Food foodItem, int quantity) {
        return findDiningCarriage()
                .map(restaurant -> restaurant.order(foodItem, quantity))
                .orElseThrow(() -> new IllegalStateException("In that train №"
                        + trainNumber + "There are no dining carriage"));
    }
}