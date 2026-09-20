package com.sveta.validator;

import com.sveta.exeptions.RequirementExceptions;
import com.sveta.factory.carriage.CarriageRequirement;
import com.sveta.factory.carriage.coupe.CoupeCarriageRequirement;
import com.sveta.factory.carriage.dining.DiningCarriageRequirement;
import com.sveta.factory.carriage.economy.EconomyCarriageRequirement;
import com.sveta.factory.carriage.seated.SeatedCarriageRequirement;

import java.util.List;

public class CarriageRequirementValidator {

    private static final int MIN_WEIGHT_KG = 1_000;
    private static final int MAX_WEIGHT_KG = 100_000;

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
        if (req.coupeRequirements().size() > 10) {
            throw new RequirementExceptions.InvalidSeatsCountException("coupe", 1, 10, req.coupeRequirements().size());
        }
    }

    private void validateSeated(SeatedCarriageRequirement req) {
        if (req.placeNumbers() <= 0 || req.placeNumbers() > 80) {
            throw new RequirementExceptions.InvalidSeatsCountException("seated", 1, 80, req.placeNumbers());
        }
    }

    private void validateDining(DiningCarriageRequirement req) {
        if (req.seatsLimit() <= 0 || req.seatsLimit() > 50) {
            throw new RequirementExceptions.InvalidSeatsCountException("dining", 1, 50, req.seatsLimit());
        }
        if (req.food() == null) {
            throw new RequirementExceptions.MissingFoodException();
        }
    }

    private void validateEconomy(EconomyCarriageRequirement req) {
        if (req.seatsLimit() <= 0 || req.seatsLimit() > 60) {
            throw new RequirementExceptions.InvalidSeatsCountException("economy", 1, 60, req.seatsLimit());
        }
    }
}