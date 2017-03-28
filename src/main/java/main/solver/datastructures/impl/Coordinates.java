package main.solver.datastructures.impl;

import main.solver.datastructures.Point;

public class Coordinates implements Point<Integer> {

    private final int x;
    private final int y;

    public Coordinates(int xPosition, int yPosition) {
        this.x = xPosition;
        this.y = yPosition;
    }

    @Override
    public Integer getXCoordinate() {
        return x;
    }

    @Override
    public Integer getYCoordinate() {
        return y;
    }
}
