package main.solver.algorithm.impl;

import main.solver.datastructures.AStarNode;
import main.solver.algorithm.AbstractPathfindingHeuristicAlgorithm;
import main.solver.algorithm.distanceheuristic.DistanceHeuristic;
import main.solver.datastructures.impl.GraphHelper;

public class AStarAlgorithm extends AbstractPathfindingHeuristicAlgorithm<AStarNode> {

    private DistanceHeuristic heuristic;

    public AStarAlgorithm(DistanceHeuristic heuristic, AStarNode start, AStarNode goal) {
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
    protected void setHeuristic(DistanceHeuristic heuristic) {
        this.heuristic = heuristic;
    }
}
