package main.solver.impl;

import main.solver.AStarNode;
import main.solver.distanceheuristic.DistanceHeuristic;

public class AStarAlgorithm extends AbstractPathfindingHeuristicAlgorithm<AStarNode> {

    private DistanceHeuristic<AStarNode> heuristic;

    public AStarAlgorithm(DistanceHeuristic<AStarNode> heuristic, AStarNode start, AStarNode goal) {
        super(start, goal);
        setHeuristic(heuristic);
        startNode.setIncrementalCost(0);
        startNode.setHeuristicCost(getHeuristicCost(startNode, goalNode));
        unexploredNodes.add(startNode);
    }

    @Override
    public void performIteration() {
        for (AStarNode neighbor : currentNode.getValidNeighbors()) {
            double successorCost = currentNode.getIncrementalCost() + GraphHelper.getCostToNeighborNode(currentNode, neighbor);
            if (!((unexploredNodes.contains(neighbor) || exploredNodes.contains(neighbor)) && neighbor.getIncrementalCost() < successorCost)) {
                neighbor.setParentNode(currentNode);
                neighbor.setIncrementalCost(successorCost);
                neighbor.setHeuristicCost(getHeuristicCost(neighbor, goalNode));
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

    @Override
    protected double getHeuristicCost(AStarNode from, AStarNode to) {
        return heuristic.getDistance(from, to);
    }

    @Override
    protected void setHeuristic(DistanceHeuristic<AStarNode> heuristic) {
        this.heuristic = heuristic;
    }
}
