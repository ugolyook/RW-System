package com.sveta.route;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Route {
    private final List<Station> stops;
    private final Directions direction;
}