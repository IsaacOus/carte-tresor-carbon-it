package org.izrak.orientation;

import java.util.Arrays;

public enum Orientation {
    NORTH("N", "W", "E") {
        @Override
        public String toString() {
            return "N";
        }
    },
    EAST("E", "N", "S") {
        @Override
        public String toString() {
            return "E";
        }
    },
    SOUTH("S", "E", "W") {
        @Override
        public String toString() {
            return "S";
        }
    },
    WEST("W", "S", "N") {
        @Override
        public String toString() {
            return "W";
        }
    };

    private final String actual;
    private final String left;
    private final String right;

    Orientation(String actual, String left, String right) {
        this.actual = actual;
        this.left = left;
        this.right = right;
    }

    public Orientation turnRight() {
        return findOrientation(right);
    }

    public Orientation turnLeft() {
        return findOrientation(left);
    }

    private Orientation findOrientation(String value) {
        return Arrays.stream(Orientation.values())
                .filter(orientation -> orientation.actual.equals(value))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("Orientation invalide"));
    }
}
