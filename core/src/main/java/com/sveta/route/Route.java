package com.sveta.route;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private final List<Station> stops;
    private final Directions direction;

    public Route(List<Station> stops, Directions direction) {
        this.stops = new ArrayList<>(stops);
        this.direction = direction;
    }

    public Directions getDirection() {
        return direction;
    }

    public List<Station> getStops() {
        return stops;
    }
}