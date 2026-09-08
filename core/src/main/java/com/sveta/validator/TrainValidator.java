package com.sveta.validator;

import com.sveta.carriage.passenger.DiningCarriage;
import com.sveta.dto.CarriageInfoDTO;
import com.sveta.exeptions.TrainExceptions;
import com.sveta.train.Locomotive;
import com.sveta.train.Train;

public class TrainValidator {
    public void isResultTrainValid(CarriageInfoDTO dto, Train train, Locomotive locomotive) {
        if (dto.getCarriages().size() > dto.getSizeLimit()) {
            throw new TrainExceptions.TrainCapacityException(dto.getCarriages().size(), dto.getSizeLimit());
        }

        long diningCount = dto.getCarriages().stream()
                .filter(c -> c instanceof DiningCarriage)
                .count();

        if (diningCount > dto.getMaxDiningCar()) {
            throw new TrainExceptions.ToManyDiningCarriageTrainException(diningCount, dto.getMaxDiningCar());
        }

        if (dto.getCarriages().size() > dto.getLengthLimit()) {
            throw new TrainExceptions.TrainCapacityException(dto.getCarriages().size(), dto.getLengthLimit());
        }

        if (!train.checkLocomotiveExists()) {
            throw new TrainExceptions.LocomotiveNotFoundException();
        }

        if (dto.getTotalCarriagesWeight() > locomotive.getMaxTransportedWeight()) {
            throw new TrainExceptions.TrainWeightExceedsLocomotiveCapacityException(
                    dto.getTotalCarriagesWeight(),
                    locomotive.getMaxTransportedWeight()
            );
        }
    }
}