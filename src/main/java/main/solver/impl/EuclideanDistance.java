package main.solver.impl;

import main.solver.DistanceHeuristic;
import main.solver.Point;

public class EuclideanDistance implements DistanceHeuristic<Point<Integer>> {
    @Override
    public double getDistance(Point<Integer> start, Point<Integer> goal) {
        return Math.sqrt(Math.pow(start.getXCoordinate() - goal.getXCoordinate(),2) + Math.pow(start.getYCoordinate() - goal.getYCoordinate(),2));
//        return Math.abs(start.getXCoordinate() - goal.getXCoordinate()) + Math.abs(start.getYCoordinate() - goal.getYCoordinate());

    }
}
