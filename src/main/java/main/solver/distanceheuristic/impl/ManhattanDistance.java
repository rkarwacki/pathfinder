package main.solver.distanceheuristic.impl;

import main.solver.Point;
import main.solver.distanceheuristic.DistanceHeuristic;

public class ManhattanDistance implements DistanceHeuristic<Point<Integer>> {
    @Override
    public double getDistance(Point<Integer> start, Point<Integer> goal) {
        return Math.abs(start.getXCoordinate() - goal.getXCoordinate()) + Math.abs(start.getYCoordinate() - goal.getYCoordinate());
    }
}
