package main.solver.impl;

import main.solver.AStarNode;
import main.solver.DistanceHeuristic;

import java.util.*;

public class Astar {

    private final DistanceHeuristic<AStarNode> heuristic;
    private final EuclideanGraph graph;
    private final NodeData start;
    private final NodeData goal;

    private Queue<AStarNode> unexploredNodes = new PriorityQueue<>();
    private List<AStarNode> exploredNodes = new ArrayList<>();
    private AstarResult result = new AstarResult();

    public Astar (EuclideanGraph graph, DistanceHeuristic<AStarNode> heuristic, NodeData startNode, NodeData goalNode) {
        this.graph = graph;
        this.heuristic = heuristic;
        this.start = startNode;
        this.goal = goalNode;
    }

    public AstarResult solve() {
        AStarNode startNode = graph.getNodeAtPosition(start.getXCoordinate(), start.getYCoordinate());
        AStarNode goalNode = graph.getNodeAtPosition(goal.getXCoordinate(), goal.getYCoordinate());
        startNode.setIncrementalCost(0);
        startNode.setHeuristicCost(heuristic.getDistance(startNode, goalNode));
        unexploredNodes.add(startNode);
        while (!unexploredNodes.isEmpty()) {
            AStarNode current = unexploredNodes.poll();
            if (current == goalNode) {
                result = reconstructPath(current);
            }
            for (AStarNode neighbor : current.getValidNeighbors()) {
                double successorCost = current.getIncrementalCost() + GraphHelper.getCostToNeighborNode(current, neighbor);
                if (!((unexploredNodes.contains(neighbor) || exploredNodes.contains(neighbor)) && neighbor.getIncrementalCost() < successorCost)) {
                    neighbor.setParentNode(current);
                    neighbor.setIncrementalCost(successorCost);
                    neighbor.setHeuristicCost(heuristic.getDistance(neighbor, goalNode));
                    if (exploredNodes.contains(neighbor)) {
                        exploredNodes.remove(neighbor);
                    }
                    if (!unexploredNodes.contains(neighbor)) {
                        unexploredNodes.add(neighbor);
                    }
                }
            }
            exploredNodes.add(current);
        }
        return result;
    }

    private AstarResult reconstructPath(AStarNode node) {
        List<AStarNode> path = new ArrayList<>();
        while (node.getParent() != null) {
            path.add(node);
            node = node.getParent();
        }
        result.setPath(path);
        result.setVisitedNodes(new ArrayList<>(exploredNodes));
        return result;
    }

}
