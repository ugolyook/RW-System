package com.sveta.factory.carriage;

import com.sveta.carriage.Carriage;

public interface CarriageFactory {
    Carriage build(CarriageRequirement req);
    boolean canBuild(CarriageRequirement req);
}
