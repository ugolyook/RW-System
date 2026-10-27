package com.sveta.train;

import com.sveta.locomotive.Locomotive;

import java.io.Serializable;

public class PassengerTrain extends Train implements Serializable {
    public PassengerTrain(int trainNumber, Locomotive locomotive)
    {
        super(trainNumber, locomotive);
    }
}