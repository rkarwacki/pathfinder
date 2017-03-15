package main.solver.impl;

import main.solver.AStarNode;
import main.solver.State;

public class GraphHelper {
    public static double getCostToNeighborNode(AStarNode current, AStarNode neighbor) {
        if (neighbor == null || (current == null)) return 0;
        else {
            if (neighbor.getState().equals(State.BLOCKED)) {
                return Integer.MAX_VALUE;
            } else if (current.getXCoordinate().equals(neighbor.getXCoordinate()) || current.getYCoordinate().equals(neighbor.getYCoordinate())) {
                return 1;
            } else return 1.4142;
        }
    }

    public static double getHeuristicCost(AStarNodeImpl start, AStarNodeImpl goal) {
        return Math.sqrt(Math.pow(start.getXCoordinate() - goal.getXCoordinate(),2) + Math.pow(start.getYCoordinate() - goal.getYCoordinate(),2));
    }
}
