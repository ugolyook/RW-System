package com.sveta.factory;

import com.sveta.carriage.Carriage;
import com.sveta.carriage.ElectricCarriage;
import com.sveta.carriage.passenger.*;
import com.sveta.dto.CarriageInfoDTO;
import com.sveta.dto.LocomotiveRequirements;
import com.sveta.exeptions.TrainExceptions;
import com.sveta.train.Locomotive;
import com.sveta.train.Train;
import com.sveta.validator.TrainValidator;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TrainFactory {
    int sizeLimit;
    int lengthLimit = 18;
    int maxDiningCar = 1;

    private static final double WEIGHT_TO_POWER_RATIO = 15.0;
    private static final double WEIGHT_TO_TRACTION_RATIO = 5.0;

    final AtomicLong trainNumber = new AtomicLong((int) System.currentTimeMillis());

    public int nextUnique() {
        return Math.toIntExact(trainNumber.incrementAndGet());
    }

    public int generateTrainNumber() {
        return nextUnique();
    }

    public TrainFactory(int sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    public void setLengthLimit(int lengthLimit) {
        this.lengthLimit = lengthLimit;
    }

    public void setMaxDiningCar(int maxDiningCar) {
        this.maxDiningCar = maxDiningCar;
    }

    public Train createTrain(int numberOfCarr, List<Carriage> carriageList) {
        boolean result = carriageList.stream()
                .allMatch(carriage -> carriage instanceof PassengerCarriage);
        if (!result) {
            throw new TrainExceptions.NotOneTypeException();
        }

        int totalWeight = carriageList.stream()
                .mapToInt(Carriage::getKgWeight)
                .sum();

        long diningCount = carriageList.stream()
                .filter(c -> c instanceof DiningCarriage)
                .count();

        if (diningCount > maxDiningCar) {
            throw new TrainExceptions.ToManyDiningCarriageTrainException(diningCount, maxDiningCar);
        }

        if (carriageList.size() > lengthLimit) {
            throw new TrainExceptions.TrainCapacityException(carriageList.size(), lengthLimit);
        }

        int requiredPower = (int) ((totalWeight / WEIGHT_TO_POWER_RATIO) * 1.2);
        int requiredTraction = (int) ((totalWeight / WEIGHT_TO_TRACTION_RATIO) * 1.2);

        boolean needElectric = carriageList.stream()
                .anyMatch(c -> c instanceof ElectricCarriage);

        LocomotiveRequirements requirements = new LocomotiveRequirements(
                requiredPower, requiredTraction, totalWeight, needElectric
        );

        Locomotive locomotive = LocomotiveFactory.findLocomotive(requirements);
        if (locomotive == null) {
            throw new RuntimeException("No suitable locomotive found!");
        }

        TrainFactory factory = new TrainFactory();
        Train train = factory.createTrain(trainNumber);
        train.setLocomotive(locomotive);

        CarriageFactory carriageFactory = new CarriageFactory();
        List<Carriage> carriages = carriageFactory.generateCarriages(numberOfCarr);

        CarriageInfoDTO dto = new CarriageInfoDTO(carriages, sizeLimit, maxDiningCar, lengthLimit);

        TrainValidator trainValidator = new TrainValidator();
        trainValidator.isResultTrainValid(dto, train);

        carriages.forEach(train::addCarriage);
        return train;
    }
}


