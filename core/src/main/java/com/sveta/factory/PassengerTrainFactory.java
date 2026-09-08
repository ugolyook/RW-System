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

    public Train createTrain(int numberOfCarr, List<Carriage> carriageList) {
        boolean isAllPassengerCarriages = carriageList.stream()
                .allMatch(carriage -> carriage instanceof PassengerCarriage);
        if (!isAllPassengerCarriages) {
            throw new TrainExceptions.NotOneTypeException();
        }

        int totalCarriagesWeightInKg = carriageList.stream()
                .mapToInt(Carriage::getKgWeight)
                .sum();

        long diningCount = carriageList.stream()
                .filter(c -> c instanceof DiningCarriage)
                .count();

        carriageValidator(carriageList, diningCount);

        int totalWeightInTons = totalCarriagesWeightInKg / 1000;

        int requiredPower = (int) ((totalCarriagesWeightInKg / WEIGHT_TO_POWER_RATIO) * SAFETY_FACTOR);
        int requiredTraction = (int) ((totalCarriagesWeightInKg / WEIGHT_TO_TRACTION_RATIO) * SAFETY_FACTOR);

        boolean isNeedElectric = carriageList.stream()
                .anyMatch(c -> c instanceof ElectricCarriage);

        LocomotiveRequirements requirements = new LocomotiveRequirements(
                requiredPower, requiredTraction, totalCarriagesWeightInKg, isNeedElectric
        );

        Locomotive locomotive = locomotiveFactory.findLocomotive(requirements);
        if (locomotive == null) {
            throw new RuntimeException("No suitable locomotive found!");
        }

        Train train = new PassengerTrain(generateTrainNumber());
        train.setLocomotive(locomotive);

        CarriageInfoDTO dto = new CarriageInfoDTO
                (carriageList, sizeLimit, maxDiningCar,
                        lengthLimit, totalWeightInTons);

        TrainValidator trainValidator = new TrainValidator();
        trainValidator.isResultTrainValid(dto, train, locomotive);

        carriageList.forEach(train::addCarriage);
        return train;
    }

    private void carriageValidator(List<Carriage> carriageList, long diningCount) {
        if (diningCount > maxDiningCar) {
            throw new TrainExceptions.ToManyDiningCarriageTrainException(diningCount, maxDiningCar);
        }

        if (carriageList.size() > lengthLimit) {
            throw new TrainExceptions.TrainCapacityException(carriageList.size(), lengthLimit);
        }
    }
}


