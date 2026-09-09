package com.sveta.exeptions;

public class TrainExceptions extends RuntimeException {

    public TrainExceptions(String s) {
    }

    public static class TrainCapacityException extends TrainExceptions {
        public TrainCapacityException(int carriageSize, int lengthLimit) {
            super("Too many carriages: " + carriageSize + " > " + lengthLimit);
        }
    }

    public static class ToManyDiningCarriageTrainException extends TrainExceptions {
        public ToManyDiningCarriageTrainException(long diningCount, int maxAllowedCount) {
            super("Too many dining cars: %s > %s".formatted(diningCount, maxAllowedCount));
        }
    }

    public static class LocomotiveNotFoundException extends TrainExceptions {
        public LocomotiveNotFoundException() {
            super("The train lacks a locomotive, it cannot move.");
        }
    }

    public static class NotOneTypeException extends TrainExceptions {
        public NotOneTypeException() {
            super("This carriage is not passenger!");
        }
    }

    public static class TrainWeightExceedsLocomotiveCapacityException extends TrainExceptions {
        public TrainWeightExceedsLocomotiveCapacityException(int totalWeightInKg, int maxWeightInKg) {
            super(String.format(
                    "Total train weight (%d kg) exceeds locomotive max capacity (%d kg)",
                    totalWeightInKg,
                    maxWeightInKg
            ));
        }
    }
}
