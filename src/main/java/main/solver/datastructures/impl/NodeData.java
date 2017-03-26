package main.solver.impl;

import main.solver.Point;

public class NodeData implements Point<Integer> {

    private final int x;
    private final int y;

    public NodeData(int xPosition, int yPosition) {
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
