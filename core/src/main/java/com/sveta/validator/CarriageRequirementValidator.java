package com.sveta.validator;

import com.sveta.exeptions.RequirementExceptions;
import com.sveta.factory.carriage.CarriageRequirement;
import com.sveta.factory.carriage.coupe.CoupeCarriageRequirement;
import com.sveta.factory.carriage.dining.DiningCarriageRequirement;
import com.sveta.factory.carriage.economy.EconomyCarriageRequirement;
import com.sveta.factory.carriage.seated.SeatedCarriageRequirement;
import com.sveta.train.carriage.passenger.models.Carriages;

import java.util.List;

public class CarriageRequirementValidator {
    private static final int MIN_WEIGHT_KG = 1_000;
    private static final int MAX_WEIGHT_KG = 100_000;

    public static final int MIN_SEATS = 1;
    public static final int MAX_COUPE_COUNT = 10;
    public static final int MAX_SEATED_PLACES = 80;
    public static final int MAX_DINING_SEATS = 50;
    public static final int MAX_ECONOMY_SEATS = 60;

    public void validateAll(List<CarriageRequirement> requirements) {
        if (requirements == null || requirements.isEmpty()) {
            throw new RequirementExceptions.EmptyList();
        }
        for (CarriageRequirement req : requirements) {
            validate(req);
        }
    }

    public void validate(CarriageRequirement requirement) {
        if (requirement == null) {
            throw new IllegalArgumentException("Requirement object cannot be null");
        }

        validateCommonWeight(requirement.getWeightInKg());

        switch (requirement) {
            case CoupeCarriageRequirement coupeReq -> validateCoupe(coupeReq);
            case SeatedCarriageRequirement seatedReq -> validateSeated(seatedReq);
            case DiningCarriageRequirement diningReq -> validateDining(diningReq);
            case EconomyCarriageRequirement economyReq -> validateEconomy(economyReq);
            default -> throw new RequirementExceptions.UnknownType(requirement.getClass().getName());
        }
    }

    private void validateCommonWeight(int weightInKg) {
        if (weightInKg < MIN_WEIGHT_KG || weightInKg > MAX_WEIGHT_KG) {
            throw new RequirementExceptions.OutOfTheWeightException(MIN_WEIGHT_KG, MAX_WEIGHT_KG, weightInKg);
        }
    }

    private void validateCoupe(CoupeCarriageRequirement req) {
        if (req.coupeRequirements() == null || req.coupeRequirements().isEmpty()) {
            throw new RequirementExceptions.EmptyCoupeListException();
        }
        if (req.coupeRequirements().size() > MAX_COUPE_COUNT) {
            throw new RequirementExceptions.InvalidSeatsCountException(
                    Carriages.COUPE,
                    MIN_SEATS,
                    MAX_COUPE_COUNT,
                    req.coupeRequirements().size()
            );
        }
    }

    private void validateSeated(SeatedCarriageRequirement req) {
        if (req.placeNumbers() <= 0 || req.placeNumbers() > MAX_SEATED_PLACES) {
            throw new RequirementExceptions.InvalidSeatsCountException(
                    Carriages.SEATED,
                    MIN_SEATS,
                    MAX_SEATED_PLACES,
                    req.placeNumbers()
            );
        }
    }

    private void validateDining(DiningCarriageRequirement req) {
        if (req.seatsLimit() <= 0 || req.seatsLimit() > MAX_DINING_SEATS) {
            throw new RequirementExceptions.InvalidSeatsCountException(
                    Carriages.DINING,
                    MIN_SEATS,
                    MAX_DINING_SEATS,
                    req.seatsLimit());
        }
        if (req.food() == null) {
            throw new RequirementExceptions.MissingFoodException();
        }
    }

    private void validateEconomy(EconomyCarriageRequirement req) {
        if (req.seatsLimit() <= 0 || req.seatsLimit() > MAX_ECONOMY_SEATS) {
            throw new RequirementExceptions.InvalidSeatsCountException(
                    Carriages.ECONOMY,
                    MIN_SEATS,
                    MAX_ECONOMY_SEATS,
                    req.seatsLimit());
        }
    }
}