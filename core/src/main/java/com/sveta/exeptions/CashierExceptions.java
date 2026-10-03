package com.sveta.exeptions;

public class CashierExceptions extends RuntimeException {
    public CashierExceptions(String message) {
        super(message);
    }

    public static class OccupiedSeatException extends CashierExceptions{
        public OccupiedSeatException() {
            super("Place was booked...");
        }
    }
}