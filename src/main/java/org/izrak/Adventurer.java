package org.izrak;

public class Adventurer {
    private final Position position;
    private final Orientation orientation;

    public Adventurer(Position position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
