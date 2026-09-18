package com.sveta.factory.carriage;

import com.sveta.train.carriage.Carriage;
import com.sveta.exeptions.TrainExceptions;

import java.util.List;
import java.util.Optional;

public class BaseCarriageFactory {
    private final List<CarriageFactory> factories;

    public BaseCarriageFactory(List<CarriageFactory> factories) {
        this.factories = factories;
    }

    public List<Carriage> createAll(List<CarriageRequirement> requirements) {
        return requirements.stream()
                .map(this::create)
                .flatMap(Optional::stream)
                .toList();
    }

    public Carriage tryToCreate(CarriageRequirement requirement) {
        return create(requirement)
                .orElseThrow(TrainExceptions.CantBuildCarriageException::new);
    }

    public Optional<Carriage> create(CarriageRequirement requirement) {
        return factories.stream()
                .filter(factory -> factory.canBuild(requirement))
                .findFirst()
                .map(factory -> factory.build(requirement));
    }
}
