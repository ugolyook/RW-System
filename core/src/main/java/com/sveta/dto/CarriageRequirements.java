package com.sveta.dto;

public class CarriageRequirements {
    private final int coupeCount;
    private final int economyCount;
    private final int seatedCount;
    private final boolean includeDiningCarriage;

    private final int coupeLimit;
    private final double coupePrice;
    private final int coupeWeightKg;
    private final int coupeBaseCarriageWeightKg;
    private final boolean coupeGenderSpecific;
    private final boolean coupePetFriendly;

    private final boolean economyBioToilets;

    private final int seatedBicycleSpots;
    private final double seatedPitchSm;

    private final boolean diningHotKitchen;
    private final boolean diningDelivery;

    public CarriageRequirements(Builder builder) {
        this.coupeCount = builder.coupeCount;
        this.economyCount = builder.economyCount;
        this.seatedCount = builder.seatedCount;
        this.includeDiningCarriage = builder.includeDiningCarriage;

        this.coupeLimit = builder.coupeLimit;
        this.coupePrice = builder.coupePrice;
        this.coupeWeightKg = builder.coupeWeightKg;
        this.coupeBaseCarriageWeightKg = builder.coupeBaseCarriageWeightKg;
        this.coupeGenderSpecific = builder.coupeGenderSpecific;
        this.coupePetFriendly = builder.coupePetFriendly;

        this.economyBioToilets = builder.economyBioToilets;

        this.seatedBicycleSpots = builder.seatedBicycleSpots;
        this.seatedPitchSm = builder.seatedPitchSm;

        this.diningHotKitchen = builder.diningHotKitchen;
        this.diningDelivery = builder.diningDelivery;
    }

    public int getTotalCarriagesCount() {
        return coupeCount + economyCount + seatedCount + (includeDiningCarriage ? 1 : 0);
    }

    public int getCoupeCount() { return coupeCount; }
    public int getEconomyCount() { return economyCount; }
    public int getSeatedCount() { return seatedCount; }
    public boolean isIncludeDiningCarriage() { return includeDiningCarriage; }

    public int getCoupeLimit() { return coupeLimit; }
    public double getCoupePrice() { return coupePrice; }
    public int getCoupeWeightKg() { return coupeWeightKg; }
    public int getCoupeBaseCarriageWeightKg() { return coupeBaseCarriageWeightKg; }
    public boolean isCoupeGenderSpecific() { return coupeGenderSpecific; }
    public boolean isCoupePetFriendly() { return coupePetFriendly; }

    public boolean isEconomyBioToilets() { return economyBioToilets; }

    public int getSeatedBicycleSpots() { return seatedBicycleSpots; }
    public double getSeatedPitchSm() { return seatedPitchSm; }

    public boolean isDiningHotKitchen() { return diningHotKitchen; }
    public boolean isDiningDelivery() { return diningDelivery; }

    public static class Builder {
        private int coupeCount = 0;
        private int economyCount = 0;
        private int seatedCount = 0;
        private boolean includeDiningCarriage = false;

        private int coupeLimit = 9;
        private double coupePrice = 20.0;
        private int coupeWeightKg = 500;
        private int coupeBaseCarriageWeightKg = 50000;
        private boolean coupeGenderSpecific = false;
        private boolean coupePetFriendly = false;

        private boolean economyBioToilets = true;

        private int seatedBicycleSpots = 4;
        private double seatedPitchSm = 80.0;

        private boolean diningHotKitchen = true;
        private boolean diningDelivery = false;

        public Builder withCoupe(int count, int coupeLimit, double price, int coupeWeightKg,
                                 int baseWeightKg, boolean genderSpecific, boolean petFriendly) {
            this.coupeCount = count;
            this.coupeLimit = coupeLimit;
            this.coupePrice = price;
            this.coupeWeightKg = coupeWeightKg;
            this.coupeBaseCarriageWeightKg = baseWeightKg;
            this.coupeGenderSpecific = genderSpecific;
            this.coupePetFriendly = petFriendly;
            return this;
        }

        public Builder withCoupe(int count, boolean genderSpecific, boolean petFriendly) {
            this.coupeCount = count;
            this.coupeGenderSpecific = genderSpecific;
            this.coupePetFriendly = petFriendly;
            return this;
        }

        public Builder withEconomy(int count, boolean bioToilets) {
            this.economyCount = count;
            this.economyBioToilets = bioToilets;
            return this;
        }

        public Builder withSeated(int count, int bicycleSpots, double pitchSm) {
            this.seatedCount = count;
            this.seatedBicycleSpots = bicycleSpots;
            this.seatedPitchSm = pitchSm;
            return this;
        }

        public Builder withDining(boolean hotKitchen, boolean delivery) {
            this.includeDiningCarriage = true;
            this.diningHotKitchen = hotKitchen;
            this.diningDelivery = delivery;
            return this;
        }

        public CarriageRequirements build() {
            return new CarriageRequirements(this);
        }
    }
}