package main.solver.impl;

import main.solver.State;

public class GraphHelper {
    public static Node findGoalNode(EuclideanGraph graph) {
        return graph.getAllNodes()
                .stream()
                .filter(node -> node.getState().equals(State.GOAL))
                .findFirst()
                .get();
    }

    public static Node findStartNode(EuclideanGraph graph) {
        return graph.getAllNodes()
                .stream()
                .filter(node -> node.getState().equals(State.START))
                .findFirst()
                .get();
    }


    public static double getCostToNeighborNode(Node current, Node neighbor) {
        if (neighbor == null || (current == neighbor)) return 0;
        else {
            if (current.getXCoordinate().equals(neighbor.getXCoordinate()) || current.getYCoordinate().equals(neighbor.getYCoordinate())) {
                return 1;
            } else return 1.4142;
        }
    }

    public static double getHeuristicCost(Node start, Node goal) {
        return Math.sqrt(Math.pow(start.getXCoordinate() - goal.getXCoordinate(),2) + Math.pow(start.getYCoordinate() - goal.getYCoordinate(),2));
    }
}
