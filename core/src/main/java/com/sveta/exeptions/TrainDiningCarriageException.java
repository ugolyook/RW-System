package com.sveta.exeptions;

public class TrainDiningCarriageException extends RuntimeException {
    public TrainDiningCarriageException() {
        super();
    }

    public TrainDiningCarriageException(String message) {
        super(message);
    }

    public TrainDiningCarriageException(String message, Throwable cause) {
        super(message, cause);
    }
}
