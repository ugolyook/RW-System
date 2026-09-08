package com.sveta.factory;

import com.sveta.dto.LocomotiveRequirements;
import com.sveta.train.Locomotive;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LocomotiveFactory {
    private final List<Locomotive> AVAILABLE_LOCOMOTIVES = new ArrayList<>();
    private static final int VL80C_WAGON_LIMIT = 80;
    private static final int VL80C_MAX_SPEED = 110;
    private static final int VL80C_MAX_TRANSPORTED_WEIGHT = 6000;
    private static final int VL80C_POWER = 6520;
    private static final int VL80C_TRACTION_FORCE = 480;
    private static final boolean VL80C_IS_ELECTRIC = true;

    private static final int VL85_WAGON_LIMIT = 100;
    private static final int VL85_MAX_SPEED = 120;
    private static final int VL85_MAX_TRANSPORTED_WEIGHT = 7000;
    private static final int VL85_POWER = 10000;
    private static final int VL85_TRACTION_FORCE = 600;
    private static final boolean VL85_IS_ELECTRIC = true;

    private static final int EP20_WAGON_LIMIT = 20;
    private static final int EP20_MAX_SPEED = 160;
    private static final int EP20_MAX_TRANSPORTED_WEIGHT = 3000;
    private static final int EP20_POWER = 8000;
    private static final int EP20_TRACTION_FORCE = 240;
    private static final boolean EP20_IS_ELECTRIC = true;

    private static final int ES4K_WAGON_LIMIT = 90;
    private static final int ES4K_MAX_SPEED = 120;
    private static final int ES4K_MAX_TRANSPORTED_WEIGHT = 6500;
    private static final int ES4K_POWER = 8800;
    private static final int ES4K_TRACTION_FORCE = 550;
    private static final boolean ES4K_IS_ELECTRIC = true;

    private static final int TEP116_WAGON_LIMIT = 70;
    private static final int TEP116_MAX_SPEED = 100;
    private static final int TEP116_MAX_TRANSPORTED_WEIGHT = 5000;
    private static final int TEP116_POWER = 4400;
    private static final int TEP116_TRACTION_FORCE = 400;
    private static final boolean TEP116_IS_ELECTRIC = false;

    private static final int TEP70_WAGON_LIMIT = 16;
    private static final int TEP70_MAX_SPEED = 160;
    private static final int TEP70_MAX_TRANSPORTED_WEIGHT = 2000;
    private static final int TEP70_POWER = 2940;
    private static final int TEP70_TRACTION_FORCE = 180;
    private static final boolean TEP70_IS_ELECTRIC = false;

    private static final int PERESVET_WAGON_LIMIT = 100;
    private static final int PERESVET_MAX_SPEED = 120;
    private static final int PERESVET_MAX_TRANSPORTED_WEIGHT = 7000;
    private static final int PERESVET_POWER = 7500;
    private static final int PERESVET_TRACTION_FORCE = 500;
    private static final boolean PERESVET_IS_ELECTRIC = false;

    private static final int TEM18_WAGON_LIMIT = 30;
    private static final int TEM18_MAX_SPEED = 80;
    private static final int TEM18_MAX_TRANSPORTED_WEIGHT = 1500;
    private static final int TEM18_POWER = 882;
    private static final int TEM18_TRACTION_FORCE = 120;
    private static final boolean TEM18_IS_ELECTRIC = false;

    {
        AVAILABLE_LOCOMOTIVES.add(
                new Locomotive(
                        VL80C_WAGON_LIMIT,
                        VL80C_MAX_SPEED,
                        VL80C_MAX_TRANSPORTED_WEIGHT,
                        VL80C_POWER,
                        VL80C_TRACTION_FORCE,
                        VL80C_IS_ELECTRIC
                )
        );

        AVAILABLE_LOCOMOTIVES.add(
                new Locomotive(
                        VL85_WAGON_LIMIT,
                        VL85_MAX_SPEED,
                        VL85_MAX_TRANSPORTED_WEIGHT,
                        VL85_POWER,
                        VL85_TRACTION_FORCE,
                        VL85_IS_ELECTRIC
                )
        );

        AVAILABLE_LOCOMOTIVES.add(
                new Locomotive(
                        EP20_WAGON_LIMIT,
                        EP20_MAX_SPEED,
                        EP20_MAX_TRANSPORTED_WEIGHT,
                        EP20_POWER,
                        EP20_TRACTION_FORCE,
                        EP20_IS_ELECTRIC
                )
        );

        AVAILABLE_LOCOMOTIVES.add(
                new Locomotive(
                        ES4K_WAGON_LIMIT,
                        ES4K_MAX_SPEED,
                        ES4K_MAX_TRANSPORTED_WEIGHT,
                        ES4K_POWER,
                        ES4K_TRACTION_FORCE,
                        ES4K_IS_ELECTRIC
                )
        );

        AVAILABLE_LOCOMOTIVES.add(
                new Locomotive(
                        TEP116_WAGON_LIMIT,
                        TEP116_MAX_SPEED,
                        TEP116_MAX_TRANSPORTED_WEIGHT,
                        TEP116_POWER,
                        TEP116_TRACTION_FORCE,
                        TEP116_IS_ELECTRIC
                )
        );

        AVAILABLE_LOCOMOTIVES.add(
                new Locomotive(
                        TEP70_WAGON_LIMIT,
                        TEP70_MAX_SPEED,
                        TEP70_MAX_TRANSPORTED_WEIGHT,
                        TEP70_POWER,
                        TEP70_TRACTION_FORCE,
                        TEP70_IS_ELECTRIC
                )
        );

        AVAILABLE_LOCOMOTIVES.add(
                new Locomotive(
                        PERESVET_WAGON_LIMIT,
                        PERESVET_MAX_SPEED,
                        PERESVET_MAX_TRANSPORTED_WEIGHT,
                        PERESVET_POWER,
                        PERESVET_TRACTION_FORCE,
                        PERESVET_IS_ELECTRIC
                )
        );

        AVAILABLE_LOCOMOTIVES.add(
                new Locomotive(
                        TEM18_WAGON_LIMIT,
                        TEM18_MAX_SPEED,
                        TEM18_MAX_TRANSPORTED_WEIGHT,
                        TEM18_POWER,
                        TEM18_TRACTION_FORCE,
                        TEM18_IS_ELECTRIC
                )
        );
    }

    public Locomotive findLocomotive(LocomotiveRequirements requirements) {
        if (Objects.isNull(requirements)) {
            throw new IllegalArgumentException("Requirements cant be null...");
        }

        return AVAILABLE_LOCOMOTIVES.stream()
                .filter(l -> l.isElectric() == requirements.isElectric())
                .filter(l -> l.getPower() >= requirements.getRequiredPower())
                .filter(l -> l.getTractionForce() >= requirements.requiredTraction())
                .filter(l -> l.getMaxTransportedWeight() >= requirements.totalWeightInKg())
                .min((l1, l2) -> compareLocomotiveRelativePwr(requirements, l1, l2))
                .orElseThrow(() -> new RuntimeException("No suitable locomotive found!"));
    }

    private static int compareLocomotiveRelativePwr(LocomotiveRequirements requirements, Locomotive l1, Locomotive l2) {
        int diff1 = l1.getPower() - requirements.getRequiredPower();
        int diff2 = l2.getPower() - requirements.getRequiredPower();
        return Integer.compare(diff1, diff2);
    }
}



