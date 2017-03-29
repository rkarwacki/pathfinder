package main.solver.datastructures.impl;

import main.solver.datastructures.AStarNode;
import main.solver.datastructures.GraphOrientedNode;
import main.solver.datastructures.NodeState;

public class GraphHelper {
    public static double getCostToNeighborNode(GraphOrientedNode current, GraphOrientedNode neighbor) {
        if (neighbor == null || (current == null)) return 0;
        else {
            if (neighbor.getState().equals(NodeState.BLOCKED)) {
                return Integer.MAX_VALUE;
            } else if (current.getXCoordinate().equals(neighbor.getXCoordinate()) || current.getYCoordinate().equals(neighbor.getYCoordinate())) {
                return 1;
            } else return 1.4142;
        }
    }

    public static double getHeuristicCost(HeuristicNodeImpl start, HeuristicNodeImpl goal) {
        return Math.sqrt(Math.pow(start.getXCoordinate() - goal.getXCoordinate(),2) + Math.pow(start.getYCoordinate() - goal.getYCoordinate(),2));
    }
}
