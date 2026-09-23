package com.sveta.route;

public class Station {
    private final String stationName;
    private final int code;

    public Station(String stationName, int code) {
        this.stationName = stationName;
        this.code = code;
    }

    @Override
    public String toString() {
        return stationName + " (code: " + code + ")";
    }

    public String getStationName() {
        return stationName;
    }
}