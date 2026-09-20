package com.sveta.factory.carriage;

import com.sveta.train.carriage.Carriage;
import com.sveta.exeptions.TrainExceptions;
import com.sveta.validator.CarriageRequirementValidator;

import java.util.List;
import java.util.Optional;

public class BaseCarriageFactory {
    private final List<CarriageFactory> factories;
    private final CarriageRequirementValidator validator;

    public BaseCarriageFactory(List<CarriageFactory> factories) {
        this.factories = factories;
        this.validator = new CarriageRequirementValidator();
    }

    public List<Carriage> createAll(List<CarriageRequirement> requirements) {
        validator.validateAll(requirements);
        return requirements.stream()
                .map(this::create)
                .flatMap(Optional::stream)
                .toList();
    }

    public Optional<Carriage> create(CarriageRequirement requirement) {
        return factories.stream()
                .filter(factory -> factory.canBuild(requirement))
                .findFirst()
                .map(factory -> factory.build(requirement));
    }
}