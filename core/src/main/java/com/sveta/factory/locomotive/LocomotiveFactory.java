package com.sveta.factory.locomotive;

import com.sveta.locomotive.Locomotive;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

@NoArgsConstructor
public class LocomotiveFactory {
    private static final Logger log = LoggerFactory.getLogger(LocomotiveFactory.class);
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

    private record LocomotiveRule(
            Predicate<LocomotiveRequirements> validator,
            String name,
            Supplier<Locomotive> creator
    ) {
    }

    public Locomotive createLocomotive(LocomotiveRequirements requirements) {
        if (Objects.isNull(requirements)) {
            throw new IllegalArgumentException("Requirements cant be null...");
        }

        log.info("Searching for a suitable locomotive. Required power: {} kW, traction: {} kN, weight: {} kg, electric: {}",
                requirements.requiredPowerInKw(),
                requirements.requiredTraction(),
                requirements.totalWeightInKg(),
                requirements.isElectric());

        if (requirements.isElectric()) {
            return createElectricLocomotive(requirements);
        } else {
            return createDieselLocomotive(requirements);
        }
    }

    private Locomotive createElectricLocomotive(LocomotiveRequirements requirements) {
        List<LocomotiveRule> rules = List.of(
                new LocomotiveRule(this::canCreateVL80C, "VL80C", this::createVL80C),
                new LocomotiveRule(this::canCreateES4K, "ES4K (Lastochka/Sinara)", this::createES4K),
                new LocomotiveRule(this::canCreateVL85, "VL85", this::createVL85),
                new LocomotiveRule(this::canCreateEP20, "EP20", this::createEP20)
        );

        return evaluateRules(rules, requirements, "electric");
    }

    private boolean canCreateVL80C(LocomotiveRequirements requirements) {
        return VL80C_POWER >= requirements.requiredPowerInKw() &&
                VL80C_TRACTION_FORCE >= requirements.requiredTraction() &&
                VL80C_MAX_TRANSPORTED_WEIGHT >= (requirements.totalWeightInKg() / 1000);
    }

    private boolean canCreateES4K(LocomotiveRequirements requirements) {
        return ES4K_POWER >= requirements.requiredPowerInKw() &&
                ES4K_TRACTION_FORCE >= requirements.requiredTraction() &&
                ES4K_MAX_TRANSPORTED_WEIGHT >= (requirements.totalWeightInKg() / 1000);
    }

    private boolean canCreateVL85(LocomotiveRequirements requirements) {
        return VL85_POWER >= requirements.requiredPowerInKw() &&
                VL85_TRACTION_FORCE >= requirements.requiredTraction() &&
                VL85_MAX_TRANSPORTED_WEIGHT >= (requirements.totalWeightInKg() / 1000);
    }

    private boolean canCreateEP20(LocomotiveRequirements requirements) {
        return EP20_POWER >= requirements.requiredPowerInKw() &&
                EP20_TRACTION_FORCE >= requirements.requiredTraction() &&
                EP20_MAX_TRANSPORTED_WEIGHT >= (requirements.totalWeightInKg() / 1000);
    }

    private Locomotive createVL80C() {
        return new Locomotive(
                VL80C_WAGON_LIMIT,
                VL80C_MAX_SPEED,
                VL80C_MAX_TRANSPORTED_WEIGHT,
                VL80C_POWER,
                VL80C_TRACTION_FORCE,
                VL80C_IS_ELECTRIC,
                "VL80C"
        );
    }

    private Locomotive createES4K() {
        return new Locomotive(
                ES4K_WAGON_LIMIT,
                ES4K_MAX_SPEED,
                ES4K_MAX_TRANSPORTED_WEIGHT,
                ES4K_POWER,
                ES4K_TRACTION_FORCE,
                ES4K_IS_ELECTRIC,
                "ES4K"
        );
    }

    private Locomotive createVL85() {
        return new Locomotive(
                VL85_WAGON_LIMIT,
                VL85_MAX_SPEED,
                VL85_MAX_TRANSPORTED_WEIGHT,
                VL85_POWER,
                VL85_TRACTION_FORCE,
                VL85_IS_ELECTRIC,
                "VL85"
        );
    }

    private Locomotive createEP20() {
        return new Locomotive(
                EP20_WAGON_LIMIT,
                EP20_MAX_SPEED,
                EP20_MAX_TRANSPORTED_WEIGHT,
                EP20_POWER,
                EP20_TRACTION_FORCE,
                EP20_IS_ELECTRIC,
                "EP20"
        );
    }

    private Locomotive createDieselLocomotive(LocomotiveRequirements requirements) {
        List<LocomotiveRule> rules = List.of(
                new LocomotiveRule(this::canCreatePeresvet, "Peresvet", this::createPeresvet),
                new LocomotiveRule(this::canCreateTEP116, "TEP116", this::createTEP116),
                new LocomotiveRule(this::canCreateTEP70, "TEP70", this::createTEP70),
                new LocomotiveRule(this::canCreateTEM18, "TEM18", this::createTEM18)
        );

        return evaluateRules(rules, requirements, "diesel");
    }

    private Locomotive evaluateRules(List<LocomotiveRule> rules, LocomotiveRequirements requirements, String type) {
        return rules.stream()
                .filter(rule -> rule.validator().test(requirements))
                .peek(rule -> log.info("Selected {} locomotive: {}", type, rule.name()))
                .map(rule -> rule.creator().get())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No suitable " + type + " locomotive found for requirements: " + requirements));
    }

    private boolean canCreatePeresvet(LocomotiveRequirements requirements) {
        return PERESVET_POWER >= requirements.requiredPowerInKw() &&
                PERESVET_TRACTION_FORCE >= requirements.requiredTraction() &&
                PERESVET_MAX_TRANSPORTED_WEIGHT >= (requirements.totalWeightInKg() / 1000);
    }

    private boolean canCreateTEP116(LocomotiveRequirements requirements) {
        return TEP116_POWER >= requirements.requiredPowerInKw() &&
                TEP116_TRACTION_FORCE >= requirements.requiredTraction() &&
                TEP116_MAX_TRANSPORTED_WEIGHT >= (requirements.totalWeightInKg() / 1000);
    }

    private boolean canCreateTEP70(LocomotiveRequirements requirements) {
        return TEP70_POWER >= requirements.requiredPowerInKw() &&
                TEP70_TRACTION_FORCE >= requirements.requiredTraction() &&
                TEP70_MAX_TRANSPORTED_WEIGHT >= (requirements.totalWeightInKg() / 1000);
    }

    private boolean canCreateTEM18(LocomotiveRequirements requirements) {
        return TEM18_POWER >= requirements.requiredPowerInKw() &&
                TEM18_TRACTION_FORCE >= requirements.requiredTraction() &&
                TEM18_MAX_TRANSPORTED_WEIGHT >= (requirements.totalWeightInKg() / 1000);
    }

    private Locomotive createPeresvet() {
        return new Locomotive(
                PERESVET_WAGON_LIMIT,
                PERESVET_MAX_SPEED,
                PERESVET_MAX_TRANSPORTED_WEIGHT,
                PERESVET_POWER,
                PERESVET_TRACTION_FORCE,
                PERESVET_IS_ELECTRIC,
                "Peresvet"
        );
    }

    private Locomotive createTEP116() {
        return new Locomotive(
                TEP116_WAGON_LIMIT,
                TEP116_MAX_SPEED,
                TEP116_MAX_TRANSPORTED_WEIGHT,
                TEP116_POWER,
                TEP116_TRACTION_FORCE,
                TEP116_IS_ELECTRIC,
                "TEP116"
        );
    }

    private Locomotive createTEP70() {
        return new Locomotive(
                TEP70_WAGON_LIMIT,
                TEP70_MAX_SPEED,
                TEP70_MAX_TRANSPORTED_WEIGHT,
                TEP70_POWER,
                TEP70_TRACTION_FORCE,
                TEP70_IS_ELECTRIC,
                "TEP70"
        );
    }

    private Locomotive createTEM18() {
        return new Locomotive(
                TEM18_WAGON_LIMIT,
                TEM18_MAX_SPEED,
                TEM18_MAX_TRANSPORTED_WEIGHT,
                TEM18_POWER,
                TEM18_TRACTION_FORCE,
                TEM18_IS_ELECTRIC,
                "TEM18"
        );
    }
}