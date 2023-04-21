package org.izrak;

import org.izrak.exception.adventurer.AdventurerException;
import org.izrak.exception.adventurer.InvalidAdventurerNameException;
import org.izrak.exception.adventurer.InvalidAdventurerStartingPositionException;

public class Adventurer {

    private final String name;
    private final Position position;
    private final Orientation orientation;

    public Adventurer(String name, Position position, Orientation orientation, Map map) throws AdventurerException {
        if (name == null || name.isEmpty()) {
            throw new InvalidAdventurerNameException("Le nom d'un aventurier ne peut pas être nul");
        }
        this.name = name;
        this.position = position;
        this.orientation = orientation;
        if (!map.isPositionInsideMap(position)) {
            throw new InvalidAdventurerStartingPositionException("La position de départ d'un aventurier doit être dans la carte");
        }
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
