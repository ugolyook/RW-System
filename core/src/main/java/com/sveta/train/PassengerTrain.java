package com.sveta.train;

import com.sveta.locomotive.Locomotive;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class PassengerTrain extends Train implements Serializable {
    public PassengerTrain(int trainNumber, Locomotive locomotive)
    {
        super(trainNumber, locomotive);
    }
}