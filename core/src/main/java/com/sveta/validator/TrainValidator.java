package com.sveta.validator;

import com.sveta.carriage.passenger.DiningCarriage;
import com.sveta.dto.CarriageInfoDTO;
import com.sveta.exeptions.TrainExceptions;
import com.sveta.train.Locomotive;
import com.sveta.train.Train;

public class TrainValidator {
    public void isResultTrainValid(CarriageInfoDTO dto, Train train, Locomotive locomotive) {
        if (dto.carriages().size() > dto.sizeLimit()) {
            throw new TrainExceptions.TrainCapacityException(dto.carriages().size(), dto.sizeLimit());
        }

        long diningCount = dto.carriages().stream()
                .filter(c -> c instanceof DiningCarriage)
                .count();

        if (diningCount > dto.maxDiningCar()) {
            throw new TrainExceptions.ToManyDiningCarriageTrainException(diningCount, dto.maxDiningCar());
        }

        if (dto.carriages().size() > dto.lengthLimit()) {
            throw new TrainExceptions.TrainCapacityException(dto.carriages().size(), dto.lengthLimit());
        }

        if (!train.checkLocomotiveExists()) {
            throw new TrainExceptions.LocomotiveNotFoundException();
        }

        if (dto.totalCarriagesWeight() > locomotive.getMaxTransportedWeight()) {
            throw new TrainExceptions.TrainWeightExceedsLocomotiveCapacityException(
                    dto.totalCarriagesWeight(),
                    locomotive.getMaxTransportedWeight()
            );
        }
    }
}