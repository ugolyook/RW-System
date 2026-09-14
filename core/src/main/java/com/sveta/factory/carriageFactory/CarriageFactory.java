package com.sveta.factory.carriageFactory;

import com.sveta.carriage.Carriage;
import com.sveta.dto.CarriageRequirements;
import com.sveta.factory.PassengerTrainFactory;

import java.util.ArrayList;
import java.util.List;

public class CarriageFactory {
    PassengerTrainFactory factory;

    public CarriageFactory(PassengerTrainFactory factory) {
        this.factory = factory;
    }

    public List<Carriage> createCarriages(CarriageRequirements requirements) {
        carriageSizeValidator(requirements.getTotalCarriagesCount());
        return buildCarriageList(requirements);
    }

    private List<Carriage> buildCarriageList(CarriageRequirements reqs) {
        List<Carriage> carriages = new ArrayList<>();

        CarriagesFactory coupeFactory = new CoupeCarriageFactory(reqs);
        CarriagesFactory economyFactory = new EconomyCarriageFactory(reqs);
        CarriagesFactory seatedFactory = new SeatedCarriageFactory(reqs);
        CarriagesFactory diningFactory = new DiningCarriageFactory(reqs);

        for (int i = 0; i < reqs.getCoupeCount(); i++) {
            carriages.add(coupeFactory.createCarriage());
        }

        if (reqs.isIncludeDiningCarriage()) {
            carriages.add(diningFactory.createCarriage());
        }

        for (int i = 0; i < reqs.getEconomyCount(); i++) {
            carriages.add(economyFactory.createCarriage());
        }

        for (int i = 0; i < reqs.getSeatedCount(); i++) {
            carriages.add(seatedFactory.createCarriage());
        }

        return carriages;
    }

    private void carriageSizeValidator(int numberOfCarr) {
        if (numberOfCarr > factory.getLengthLimit()) {
            throw new IllegalArgumentException("Number of carriages " +
                    "(" + numberOfCarr + ") exceeds size limit (" + factory.getSizeLimit() + ")"
            );
        }
    }
}