package main.solver.algorithm.distanceheuristic.impl;

import main.solver.algorithm.distanceheuristic.DistanceHeuristic;
import main.solver.datastructures.Point;

public class EuclideanDistance implements DistanceHeuristic<Point<Integer>> {
    @Override
    public double getDistance(Point<Integer> start, Point<Integer> goal) {
        return Math.sqrt(Math.pow(start.getXCoordinate() - goal.getXCoordinate(),2) + Math.pow(start.getYCoordinate() - goal.getYCoordinate(),2));
    }
}
