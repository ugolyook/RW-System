package com.sveta.validator;

import com.sveta.carriage.passenger.DiningCarriage;
import com.sveta.exeptions.TrainCapacityException;
import com.sveta.exeptions.TrainDiningCarriageException;
import com.sveta.dto.CarriageInfoDTO;

public class TrainValidator {
    public boolean isResultTrainValid(CarriageInfoDTO dto) {
        if (dto.getCarriages().size() > dto.getSizeLimit()) {
            throw new TrainCapacityException("Size limit exceeded: "
                    + dto.getCarriages().size()
                    + " > " + dto.getSizeLimit());
        }

        long diningCount = dto.getCarriages().stream()
                .filter(c -> c instanceof DiningCarriage)
                .count();

        if (diningCount > dto.getMaxDiningCar()) {
            throw new TrainDiningCarriageException("Too many dining cars: "
                    + diningCount + " > "
                    + dto.getMaxDiningCar());
        }

        if (dto.getCarriages().size() > dto.getLengthLimit()) {
            throw new TrainCapacityException("Too many carriages: " + dto.getCarriages().size() +
                    " > " + dto.getLengthLimit());
        }
        //добавить проверку на наличие локомотива

        return true;
    }
}
