package org.izrak.orientation;

public enum Orientation {
    N, E, S, W;

    public Orientation turnRight() {
        switch (this) {
            case N:
                return E;
            case E:
                return S;
            case S:
                return W;
            case W:
                return N;
            default:
                return null;
        }
    }

    public Orientation turnLeft() {
        switch (this) {
            case N:
                return W;
            case E:
                return N;
            case S:
                return E;
            case W:
                return S;
            default:
                return null;
        }
    }
}
