package com.sveta.route;

import java.util.List;

public record Route(
        List<Station> stops,
        Directions direction
) {
}