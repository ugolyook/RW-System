package com.sveta.locomotive;

public record Locomotive(
        int wagonLimit,
        int maxSpeed,
        int maxTransportedWeight,
        int power,
        int tractionForce,
        boolean electric
) {
}