package com.ui.strategies;

import com.ui.model.Planet;

public class NameMatchingStrategy implements MatchingStrategy {

    private String planetName;

    public NameMatchingStrategy(String planetName) {
        this.planetName = planetName;
    }

    @Override
    public boolean match(Planet planet) {
        return planet.getName().equalsIgnoreCase(this.planetName);
    }
}
