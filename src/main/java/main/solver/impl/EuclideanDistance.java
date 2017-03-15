package main.solver.impl;

import main.solver.AStarNode;
import main.solver.DistanceHeuristic;

public class EuclideanDistance implements DistanceHeuristic<AStarNode> {
    @Override
    public double getDistance(AStarNode start, AStarNode goal) {
        return Math.sqrt(Math.pow(start.getXCoordinate() - goal.getXCoordinate(),2) + Math.pow(start.getYCoordinate() - goal.getYCoordinate(),2));

    }
}
