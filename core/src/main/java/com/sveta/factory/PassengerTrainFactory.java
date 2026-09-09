package com.sveta.factory;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.ElectricCarriage;
import com.sveta.carriage.passenger.*;
import com.sveta.dto.CarriageInfoDTO;
import com.sveta.dto.LocomotiveRequirements;
import com.sveta.exeptions.TrainExceptions;
import com.sveta.train.Locomotive;
import com.sveta.train.PassengerTrain;
import com.sveta.train.Train;
import com.sveta.validator.TrainValidator;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class PassengerTrainFactory {
    int sizeLimit;
    int lengthLimit = 18;
    int maxDiningCar = 1;

    private static final double WEIGHT_TO_POWER_RATIO = 15.0;
    private static final double WEIGHT_TO_TRACTION_RATIO = 5.0;
    private static final double SAFETY_FACTOR = 1.0;

    private final CarriageFactory carriageFactory = new CarriageFactory();
    private final LocomotiveFactory locomotiveFactory = new LocomotiveFactory();

    final AtomicLong trainNumber = new AtomicLong((int) System.currentTimeMillis());

    public int nextUnique() {
        return Math.toIntExact(trainNumber.incrementAndGet());
    }

    public int generateTrainNumber() {
        return nextUnique();
    }

    public PassengerTrainFactory(int sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    public void setLengthLimit(int lengthLimit) {
        this.lengthLimit = lengthLimit;
    }

    public void setMaxDiningCar(int maxDiningCar) {
        this.maxDiningCar = maxDiningCar;
    }

    public CarriageFactory getCarriageFactory() {
        return carriageFactory;
    }

    public Train createTrain(List<Carriage> carriageList) {
        validateAllPassengerCarriage(carriageList);

        int totalCarriagesWeightInKg = calculateTotalWeight(carriageList);
        long diningCount = countDiningCarriages(carriageList);

        validateCarriageLimits(carriageList, diningCount);

        int totalWeightInTons = totalCarriagesWeightInKg / 1000;

        int requiredPower = (int) ((totalCarriagesWeightInKg / WEIGHT_TO_POWER_RATIO) * SAFETY_FACTOR);
        int requiredTraction = (int) ((totalCarriagesWeightInKg / WEIGHT_TO_TRACTION_RATIO) * SAFETY_FACTOR);

        boolean isNeedElectric = carriageList.stream()
                .anyMatch(c -> c instanceof ElectricCarriage);

        LocomotiveRequirements requirements = new LocomotiveRequirements(
                requiredPower, requiredTraction, totalCarriagesWeightInKg, isNeedElectric
        );

        Locomotive locomotive = findSuitableLocomotive(requirements);

        Train train = new PassengerTrain(generateTrainNumber());
        train.setLocomotive(locomotive);

        validateTrainWithValidator(carriageList, totalWeightInTons, train, locomotive);

        carriageList.forEach(train::addCarriage);
        return train;
    }

    private void validateTrainWithValidator(List<Carriage> carriageList, int totalWeightInTons, Train train, Locomotive locomotive) {
        CarriageInfoDTO dto = new CarriageInfoDTO
                (carriageList, sizeLimit, maxDiningCar,
                        lengthLimit, totalWeightInTons);

        TrainValidator trainValidator = new TrainValidator();
        trainValidator.isResultTrainValid(dto, train, locomotive);
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
        if (diningCount > maxDiningCar) {
            throw new TrainExceptions.ToManyDiningCarriageTrainException(diningCount, maxDiningCar);
        }

        if (carriageList.size() > lengthLimit) {
            throw new TrainExceptions.TrainCapacityException(carriageList.size(), lengthLimit);
        }
    }
}


