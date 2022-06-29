package com.ui.strategies;

import com.ui.model.Planet;

public class RadiusMatchingStrategy implements MatchingStrategy {

    private double radius;

    public RadiusMatchingStrategy(double radius){
        this.radius = radius;
    }

    @Override
    public boolean match(Planet planet) {
        return planet.getPlanetradius() == this.radius;
    }
}
