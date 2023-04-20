package org.izrak;

public class Adventurer {

    private final String name;
    private final Position position;
    private final Orientation orientation;

    public Adventurer(String name, Position position, Orientation orientation) {
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
