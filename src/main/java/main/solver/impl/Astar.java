package main.solver.impl;

import main.solver.State;

import java.util.*;

public class Astar {
    public List<Node> solve (EuclideanGraph graph) {
        List<Node> path = Collections.emptyList();
        TreeSet<Node> openSet = new TreeSet<>();
        Set<Node> closedSet = new HashSet<>();
        Node startNode = GraphHelper.findStartNode(graph);
        Node goalNode = GraphHelper.findGoalNode(graph);
        startNode.setIncrementalCost(0);
        startNode.setHeuristicCost(GraphHelper.getHeuristicCost(startNode, goalNode));
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node current = openSet.first();
            if (current.getState().equals(State.GOAL)) {
                path = reconstructPath(goalNode);
            }
            openSet.remove(current);
            closedSet.add(current);
            for (Node neighbor : current.getNeighbors()) {
                if (!closedSet.contains(neighbor)) {
                    neighbor.setIncrementalCost(GraphHelper.getCostToNeighborNode(current, neighbor));
                    neighbor.setParentNode(current);
                    neighbor.setHeuristicCost(GraphHelper.getHeuristicCost(current, goalNode));
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    } else {
                        Set<Node> openSetCcpy = new HashSet<>(openSet);
                        Set<Node> neighbors = new HashSet<>(current.getNeighbors());
                        openSetCcpy.retainAll(neighbors);
                        for (Node openNeighbor : openSetCcpy) {
                            if (neighbor.getIncrementalCost() <= openNeighbor.getIncrementalCost()) {
                                openNeighbor.setIncrementalCost(neighbor.getIncrementalCost());
                                openNeighbor.setParentNode(neighbor.getParent());
                            }
                        }
                    }
                }
            }
        }
        return path;
    }

    private List<Node> reconstructPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node.getParent() != null) {
            path.add(node);
            node = node.getParent();
        }
        return path;
    }


}
