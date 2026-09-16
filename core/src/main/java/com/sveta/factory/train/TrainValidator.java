package com.sveta.factory.train;

import com.sveta.carriage.passenger.DiningCarriage;
import com.sveta.dto.CarriageInfoDTO;
import com.sveta.exeptions.TrainExceptions;
import com.sveta.train.Locomotive;
import com.sveta.train.Train;

public class TrainValidator {
    public void isResultTrainValid(CarriageInfoDTO dto, Train train, Locomotive locomotive) {
        carriageSizeChecker(dto, dto.sizeLimit());

        diningCarriageChecker(dto);

        carriageSizeChecker(dto, dto.lengthLimit());

        ifLocomotiveExistsChecker(train);

        carriageWeightChecker(dto, locomotive);
    }

    public void carriageWeightChecker(CarriageInfoDTO dto, Locomotive locomotive) {
        if (dto.totalCarriagesWeight() > locomotive.getMaxTransportedWeight()) {
            throw new TrainExceptions.TrainWeightExceedsLocomotiveCapacityException(
                    dto.totalCarriagesWeight(),
                    locomotive.getMaxTransportedWeight()
            );
        }
    }

    public void ifLocomotiveExistsChecker(Train train) {
        if (!train.checkLocomotiveExists()) {
            throw new TrainExceptions.LocomotiveNotFoundException();
        }
    }

    public void diningCarriageChecker(CarriageInfoDTO dto) {
        long diningCount = dto.carriages().stream()
                .filter(c -> c instanceof DiningCarriage)
                .count();

        if (diningCount > dto.maxDiningCar()) {
            throw new TrainExceptions.ToManyDiningCarriageTrainException(diningCount, dto.maxDiningCar());
        }
    }

    public void carriageSizeChecker(CarriageInfoDTO dto, int dto1) {
        if (dto.carriages().size() > dto1) {
            throw new TrainExceptions.TrainCapacityException(dto.carriages().size(), dto1);
        }
    }
}