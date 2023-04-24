package org.izrak.map;

import org.izrak.orientation.Orientation;

import java.util.*;

import static org.izrak.orientation.Orientation.*;

public class Map {
    private final int width;
    private final int height;
    private final Set<Position> mountains;
    private final List<Position> treasures;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.mountains = new HashSet<>();
        this.treasures = new ArrayList<>();
    }

    public Map(int width, int height, Set<Position> mountains) {
        this.width = width;
        this.height = height;
        this.mountains = mountains;
        this.treasures = new ArrayList<>();
    }

    public Map(int width, int height, Set<Position> mountains, List<Position> treasures) {
        this.width = width;
        this.height = height;
        this.mountains = mountains;
        this.treasures = treasures;
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

        return mountains.contains(newPosition) || isPositionNotInsideMap(newPosition) ? position : newPosition;

    }

    public boolean isPositionNotInsideMap(Position position) {
        return position.getX() < 0 || position.getX() >= width || position.getY() < 0 || position.getY() >= height;
    }


    public boolean isTreasureAtPosition(Position position) {
        for (Position treasure : treasures) {
            if (treasure.equals(position)) {
                treasures.remove(position);
                return true;
            }
        }
        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Position> getTreasures() {
        return treasures;
    }

    public Set<Position> getMountains() {
        return mountains;
    }

}
