package main.solver.algorithm.impl;

import main.solver.algorithm.AbstractPathfindingAlgorithm;
import main.solver.datastructures.impl.GraphHelper;
import main.solver.datastructures.impl.GraphNode;

public class DijkstraAlgorithm extends AbstractPathfindingAlgorithm<GraphNode> {

    public DijkstraAlgorithm(GraphNode startNode, GraphNode goalNode) {
        super(startNode, goalNode);
    }

    @Override
    public void performIteration() {
        for (GraphNode neighbor : currentNode.getValidNeighbors()) {
            double successorCost = currentNode.getIncrementalCost() + GraphHelper.getCostToNeighborNode(currentNode, neighbor);
            if (!((unexploredNodes.contains(neighbor) || exploredNodes.contains(neighbor)) && neighbor.getIncrementalCost() < successorCost)) {
                neighbor.setParentNode(currentNode);
                neighbor.setIncrementalCost(successorCost);
                if (exploredNodes.contains(neighbor)) {
                    exploredNodes.remove(neighbor);
                }
                if (!unexploredNodes.contains(neighbor)) {
                    unexploredNodes.add(neighbor);
                }
            }
        }
        exploredNodes.add(currentNode);
    }
}
