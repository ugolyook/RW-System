package com.sveta.factory.carriage;

import com.sveta.train.carriage.Carriage;

public interface CarriageFactory {
    Carriage build(CarriageRequirement req);
    boolean canBuild(CarriageRequirement req);
}
