package com.sveta.factory.train;

import com.sveta.train.carriage.Carriage;
import com.sveta.train.carriage.ElectricCarriage;
import com.sveta.factory.locomotive.LocomotiveRequirements;
import com.sveta.exeptions.TrainExceptions;
import com.sveta.factory.locomotive.LocomotiveFactory;
import com.sveta.locomotive.Locomotive;
import com.sveta.train.PassengerTrain;
import com.sveta.train.Train;
import com.sveta.train.carriage.passenger.DiningCarriage;
import com.sveta.train.carriage.passenger.PassengerCarriage;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class PassengerTrainFactory {
    final AtomicLong trainNumber = new AtomicLong((int) System.currentTimeMillis());

    private static final double WEIGHT_TO_POWER_RATIO = 15.0;
    private static final double WEIGHT_TO_TRACTION_RATIO = 5.0;
    private static final double SAFETY_FACTOR = 1.0;

    private final LocomotiveFactory locomotiveFactory;

    public int nextUnique() {
        return Math.toIntExact(trainNumber.incrementAndGet());
    }

    public int generateTrainNumber() {
        return nextUnique();
    }

    public PassengerTrainFactory(int sizeLimit, LocomotiveFactory locomotiveFactory) {
        this.locomotiveFactory = locomotiveFactory;
    }

    public Train createTrain(List<Carriage> carriageList) {
        validateAllPassengerCarriage(carriageList);

        int totalCarriagesWeightInKg = calculateTotalWeight(carriageList);
        long diningCount = countDiningCarriages(carriageList);

        validateCarriageLimits(carriageList, diningCount);

        int requiredPower = (int) ((totalCarriagesWeightInKg / WEIGHT_TO_POWER_RATIO) * SAFETY_FACTOR);
        int requiredTraction = (int) ((totalCarriagesWeightInKg / WEIGHT_TO_TRACTION_RATIO) * SAFETY_FACTOR);

        boolean isNeedElectric = carriageList.stream()
                .anyMatch(c -> c instanceof ElectricCarriage);

        LocomotiveRequirements requirements = new LocomotiveRequirements(
                requiredPower, requiredTraction, totalCarriagesWeightInKg, isNeedElectric
        );

        Locomotive locomotive = findSuitableLocomotive(requirements);

        Train train = new PassengerTrain(generateTrainNumber(), locomotive);

        carriageList.forEach(train::addCarriage);
        return train;
    }

    private Locomotive findSuitableLocomotive(LocomotiveRequirements requirements) {
        return locomotiveFactory.createLocomotive(requirements);
    }

    private static long countDiningCarriages(List<Carriage> carriageList) {
        return carriageList.stream()
                .filter(c -> c instanceof DiningCarriage)
                .count();
    }

    private static int calculateTotalWeight(List<Carriage> carriageList) {
        return carriageList.stream()
                .mapToInt(Carriage::getKgWeight)
                .sum();
    }

    private static void validateAllPassengerCarriage(List<Carriage> carriageList) {
        boolean isAllPassengerCarriages = carriageList.stream()
                .allMatch(carriage -> carriage instanceof PassengerCarriage);
        if (!isAllPassengerCarriages) {
            throw new TrainExceptions.NotOneTypeException();
        }
    }

    private void validateCarriageLimits(List<Carriage> carriageList, long diningCount) {
        int maxDiningCar = 1;
        if (diningCount > maxDiningCar) {
            throw new TrainExceptions.ToManyDiningCarriageTrainException(diningCount, maxDiningCar);
        }

        int lengthLimit = 18;
        if (carriageList.size() > lengthLimit) {
            throw new TrainExceptions.TrainCapacityException(carriageList.size(), lengthLimit);
        }
    }
}