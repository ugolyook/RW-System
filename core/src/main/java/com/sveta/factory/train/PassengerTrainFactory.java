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
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor
public class PassengerTrainFactory {
    private static final Logger log = LoggerFactory.getLogger(PassengerTrainFactory.class);
    final AtomicLong trainNumber = new AtomicLong(100);

    private static final double WEIGHT_TO_POWER_RATIO = 60.0;
    private static final double WEIGHT_TO_TRACTION_RATIO = 15.0;
    private static final double SAFETY_FACTOR = 1.0;

    private final int sizeLimit;
    private final LocomotiveFactory locomotiveFactory;

    public int nextUnique() {
        return Math.toIntExact(trainNumber.incrementAndGet());
    }

    public int generateTrainNumber() {
        return nextUnique();
    }

    public Train createTrain(List<Carriage> carriageList) {
        log.info("Initiating train creation. Total carriages requested: {}", carriageList.size());
        validateAllPassengerCarriage(carriageList);
        boolean isNeedElectric = validateElectricCarriagesConsistency(carriageList);

        int totalCarriagesWeightInKg = calculateTotalWeight(carriageList);
        long diningCount = countDiningCarriages(carriageList);

        validateCarriageLimits(carriageList, diningCount);

        int requiredPower = (int) ((totalCarriagesWeightInKg / WEIGHT_TO_POWER_RATIO) * SAFETY_FACTOR);
        int requiredTractionInKg = (int) ((totalCarriagesWeightInKg / WEIGHT_TO_TRACTION_RATIO) * SAFETY_FACTOR);
        int requiredTractionInKn = (requiredTractionInKg / 100);

        log.info("Train metrics computed: Weight = {} kg, Power = {} kW, Traction = {} kN, Electric = {}",
                totalCarriagesWeightInKg, requiredPower, requiredTractionInKn, isNeedElectric);

        LocomotiveRequirements requirements = LocomotiveRequirements.builder()
                .requiredPowerInKw(requiredPower)
                .requiredTraction(requiredTractionInKn)
                .totalWeightInKg(totalCarriagesWeightInKg)
                .isElectric(isNeedElectric)
                .build();

        Locomotive locomotive = findSuitableLocomotive(requirements);

        Train train = new PassengerTrain(generateTrainNumber(), locomotive);

        carriageList.forEach(train::addCarriage);
        log.info("Train №{} successfully built with assigned locomotive.", train.getTrainNumber());
        return train;
    }

    private Locomotive findSuitableLocomotive(LocomotiveRequirements requirements) {
        log.debug("Requesting matching locomotive from LocomotiveFactory...");
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
            log.error("Validation failed: Non-passenger carriage detected in a passenger train composition.");
            throw new TrainExceptions.NotOneTypeException();
        }
    }

    private static boolean validateElectricCarriagesConsistency(List<Carriage> carriageList) {
        boolean hasElectric = carriageList.stream().anyMatch(ElectricCarriage.class::isInstance);
        boolean hasNonElectric = carriageList.stream().anyMatch(c -> !(c instanceof ElectricCarriage));

        if (hasElectric && hasNonElectric) {
            log.error("Validation failed: Attempted to mix electric and non-electric carriages in one train.");
            throw new TrainExceptions("Train cannot contain a mix of electric and non-electric carriages!");
        }

        return hasElectric;
    }

    private void validateCarriageLimits(List<Carriage> carriageList, long diningCount) {
        int maxDiningCar = 1;
        if (diningCount > maxDiningCar) {
            log.error("Validation failed: Too many dining cars. Provided: {}, Allowed: {}", diningCount, maxDiningCar);
            throw new TrainExceptions.ToManyDiningCarriageTrainException(diningCount, maxDiningCar);
        }

        if (carriageList.size() > this.sizeLimit) {
            log.error("Validation failed: Train capacity limit exceeded. Size: {}, Limit: {}", carriageList.size(), this.sizeLimit);
            throw new TrainExceptions.TrainCapacityException(carriageList.size(), this.sizeLimit);
        }
    }
}