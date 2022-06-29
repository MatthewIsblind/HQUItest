package com.ui.strategies;

import com.ui.model.Planet;

public class SunDistanceMatchingStrategy implements MatchingStrategy{

    private long distanceFromSun;

    public SunDistanceMatchingStrategy(long DistanceFromSun) {
        this.distanceFromSun = DistanceFromSun;
    }

    @Override
    public boolean match(Planet planet) {
        return planet.getDistanceFromSun() == this.distanceFromSun;
    }
}
