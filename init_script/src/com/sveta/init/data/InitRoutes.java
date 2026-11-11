package com.sveta.init.data;

import com.sveta.route.Directions;
import com.sveta.route.Route;
import com.sveta.route.Station;

import java.util.List;

public class InitRoutes {
    private static final Station MINSK = new Station("Minsk", 2200001);
    private static final Station MINSK_PASS = new Station("Minsk-Passenger", 2200020);
    private static final Station MOGILEV = new Station("Mogilev", 2200030);
    private static final Station MOGILEV_CENTRAL = new Station("Mogilev Central", 2200060);
    private static final Station BARANOVICHI = new Station("Baranovichi", 2100010);
    private static final Station BREST = new Station("Brest", 2100050);
    private static final Station OSIPOVICHI = new Station("Osipovichi", 2200040);
    private static final Station GOMEL = new Station("Gomel", 2300070);

    public Route minskMogilev() {
        return new Route(List.of(MINSK, MINSK_PASS, MOGILEV, MOGILEV_CENTRAL), Directions.FORWARD);
    }

    public Route minskBrest() {
        return new Route(List.of(MINSK, BARANOVICHI, BREST), Directions.FORWARD);
    }

    public Route minskGomel() {
        return new Route(List.of(MINSK, OSIPOVICHI, GOMEL), Directions.FORWARD);
    }
}