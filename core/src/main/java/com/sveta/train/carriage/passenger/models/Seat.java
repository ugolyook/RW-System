package com.sveta.train.carriage.passenger.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Getter
@RequiredArgsConstructor
public class Seat {
    private final int number;
    private final SeatType type;
}