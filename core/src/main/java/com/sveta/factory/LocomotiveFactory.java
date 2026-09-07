package com.sveta.factory;

import com.sveta.dto.LocomotiveRequirements;
import com.sveta.train.Locomotive;

import java.util.ArrayList;
import java.util.List;

public class LocomotiveFactory {
    private static final List<Locomotive> AVAILABLE_LOCOMOTIVES = new ArrayList<>();

    static {
        // ВЛ80С - грузовой, 2 секции, мощность 6520 кВт
        AVAILABLE_LOCOMOTIVES.add(
                new Locomotive(80, 110, 6000,
                        6520, 480, true)
        );

        // ВЛ85 - грузовой, 2 секции, мощность 10000 кВт
        AVAILABLE_LOCOMOTIVES.add(
                new Locomotive(100, 120, 7000,
                        10000, 600, true)
        );

        // ЭП20 - пассажирский, мощность 8000 кВт
        AVAILABLE_LOCOMOTIVES.add(
                new Locomotive(20, 160, 3000,
                        8000, 240, true)
        );

        // ЭС4К "Дончак" - грузовой, мощность 8800 кВт
        AVAILABLE_LOCOMOTIVES.add(
                new Locomotive(90, 120, 6500,
                        8800, 550, true)
        );

        // 2ТЭ116 - грузовой, 2 секции, мощность 4400 кВт
        AVAILABLE_LOCOMOTIVES.add(
                new Locomotive(70, 100, 5000,
                        4400, 400, false)
        );

        // ТЭП70 - пассажирский, мощность 2940 кВт
        AVAILABLE_LOCOMOTIVES.add(
                new Locomotive(16, 160, 2000,
                        2940, 180, false)
        );

        // 3ТЭ25К2М "Пересвет" - грузовой, 3 секции, мощность 7500 кВт
        AVAILABLE_LOCOMOTIVES.add(
                new Locomotive(100, 120, 7000,
                        7500, 500, false)
        );

        // ТЭМ18 - маневровый, мощность 882 кВт
        AVAILABLE_LOCOMOTIVES.add(
                new Locomotive(30, 80, 1500,
                        882, 120, false)
        );
    }

    public static Locomotive findLocomotive(LocomotiveRequirements requirements) {
        if (requirements == null) {
            throw new IllegalArgumentException("Requirements cant be null...");
        }

        List<Locomotive> candidates = AVAILABLE_LOCOMOTIVES.stream()
                .filter(l -> l.isElectric() == requirements.isElectric())
                .filter(l -> l.getPower() >= requirements.getRequiredPower())
                .filter(l -> l.getTractionForce() >= requirements.getRequiredTraction())
                .filter(l -> l.getMaxTransportedWeight() >= requirements.getTotalWeight())
                .toList();

        if (candidates.isEmpty()) {
            return null;
        }

        return candidates.stream()
                .min((l1, l2) -> {
                    int diff1 = l1.getPower() - requirements.getRequiredPower();
                    int diff2 = l2.getPower() - requirements.getRequiredPower();
                    return Integer.compare(diff1, diff2);
                })
                .orElse(null);
    }
}



