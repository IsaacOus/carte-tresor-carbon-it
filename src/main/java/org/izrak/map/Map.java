package org.izrak.map;

import org.izrak.orientation.Orientation;

import java.util.HashSet;
import java.util.Set;

import static org.izrak.orientation.Orientation.*;

public class Map {
    private final int width;
    private final int height;
    private final Set<Position> mountains;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.mountains = new HashSet<>();
    }

    public Map(int width, int height, Set<Position> mountains) {
        this.width = width;
        this.height = height;
        this.mountains = mountains;
    }


    public Position getNextPosition(Position position, Orientation orientation) {
        int x = position.getX();
        int y = position.getY();

        if (orientation == NORTH) {
            y++;
        } else if (orientation == SOUTH) {
            y--;
        } else if (orientation == EAST) {
            x++;
        } else if (orientation == WEST) {
            x--;
        }

        Position newPosition = new Position(x, y);

        return mountains.contains(newPosition) || !isPositionInsideMap(newPosition) ? position : newPosition;

    }

    public boolean isPositionInsideMap(Position position) {
        return position.getX() >= 0 && position.getX() < width && position.getY() >= 0 && position.getY() < height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
