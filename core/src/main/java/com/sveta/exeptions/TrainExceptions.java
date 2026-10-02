package com.sveta.exeptions;

public class TrainExceptions extends RuntimeException {

    public TrainExceptions(String s) {
        super(s);
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

    public static class NotOneTypeException extends TrainExceptions {
        public NotOneTypeException() {
            super("This carriage is not passenger!");
        }
    }
}