package main.solver.algorithm.distanceheuristic.impl;

import main.solver.datastructures.Point;
import main.solver.algorithm.distanceheuristic.DistanceHeuristic;

public class ManhattanDistance implements DistanceHeuristic<Point<Integer>> {
    @Override
    public double getDistance(Point<Integer> start, Point<Integer> goal) {
        return Math.abs(start.getXCoordinate() - goal.getXCoordinate()) + Math.abs(start.getYCoordinate() - goal.getYCoordinate());
    }
}
