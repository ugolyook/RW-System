package com.sveta.exeptions;

public class TrainCapacityException extends RuntimeException{
    public TrainCapacityException() {
        super();
    }

    public TrainCapacityException(String message) {
        super(message);
    }

    public TrainCapacityException(String message, Throwable cause) {
        super(message, cause);
    }
}
