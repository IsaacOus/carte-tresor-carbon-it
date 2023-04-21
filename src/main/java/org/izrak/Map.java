package org.izrak;

public class Map {
    private final int width;
    private final int height;

        public Map(int width, int height) {
            this.width = width;
            this.height = height;
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
