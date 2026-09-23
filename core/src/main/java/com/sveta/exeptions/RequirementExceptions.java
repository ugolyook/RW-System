package com.sveta.exeptions;

public class RequirementExceptions extends RuntimeException {
    public RequirementExceptions(String message) {
        super(message);
    }

    public static class EmptyList extends RequirementExceptions {
        public EmptyList() {
            super("List of requirement can't be empty");
        }
    }

    public static class UnknownType extends RequirementExceptions {
        public UnknownType(String className) {
            super("Unknown requirement type..." + className);
        }
    }

    public static class OutOfTheWeightException extends RequirementExceptions {
        public OutOfTheWeightException(int minWeight, int maxWeight, int weightInKg) {
            super("The weight of the wagon must be from " + minWeight + "\n" +
                    " to " + maxWeight + " kg. Receive:" + weightInKg);
        }
    }

    public static class InvalidSeatsCountException extends RequirementExceptions {
        public InvalidSeatsCountException(String carriageType, int minSeats, int maxSeats, int actualSeats) {
            super("Seats count in " + carriageType + " carriage must be from " +
                    minSeats + " to " + maxSeats + ". Received: " + actualSeats);
        }
    }

    public static class MissingFoodException extends RequirementExceptions {
        public MissingFoodException() {
            super("Food cannot be null for dining carriage");
        }
    }

    public static class EmptyCoupeListException extends RequirementExceptions {
        public EmptyCoupeListException() {
            super("Coupe list cannot be empty");
        }
    }
}

