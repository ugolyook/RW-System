package com.sveta.route;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class Station {
    private final String stationName;
    private final int code;
}