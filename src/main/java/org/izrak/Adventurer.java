package org.izrak;

import org.izrak.exception.InvalidAdventurerNameException;

public class Adventurer {

    private final String name;
    private final Position position;
    private final Orientation orientation;

    public Adventurer(String name, Position position, Orientation orientation) throws InvalidAdventurerNameException {
        if (name == null || name.isEmpty()) {
            throw new InvalidAdventurerNameException("Le nom d'un aventurier ne peut pas être nul");
        }
        this.name = name;
        this.position = position;
        this.orientation = orientation;
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
