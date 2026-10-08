package com.sveta.locomotive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Locomotive {
    private final int wagonLimit;
    private final int maxSpeed;
    private final int maxTransportedWeight;
    private final int power;
    private final int tractionForce;
    private final boolean electric;
}