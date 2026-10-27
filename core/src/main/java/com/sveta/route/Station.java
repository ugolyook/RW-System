package com.sveta.route;

import lombok.Builder;

@Builder
public record Station(
        String stationName,
        int code
) {
}