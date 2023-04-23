package org.izrak.map;

import java.util.HashSet;
import java.util.Set;

public class Map {
    private final int width;
    private final int height;
    private final Set<Mountain> mountains;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.mountains = new HashSet<>();
    }

    public Map(int width, int height, Set<Mountain> mountainPositions) {
        this.width = width;
        this.height = height;
        this.mountains = mountainPositions;
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

    public Set<Mountain> getMountains() {
        return mountains;
    }

    public void addMountain(Mountain mountain) {
        mountains.add(mountain);
    }
}
