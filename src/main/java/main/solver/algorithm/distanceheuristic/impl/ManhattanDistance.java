package main.solver.algorithm.distanceheuristic.impl;

import main.solver.datastructures.AStarNode;
import main.solver.datastructures.Point;
import main.solver.algorithm.distanceheuristic.DistanceHeuristic;

public class ManhattanDistance implements DistanceHeuristic {
    @Override
    public double getDistance(AStarNode start, AStarNode goal) {
        return Math.abs(start.getXCoordinate() - goal.getXCoordinate()) + Math.abs(start.getYCoordinate() - goal.getYCoordinate());
    }
}
