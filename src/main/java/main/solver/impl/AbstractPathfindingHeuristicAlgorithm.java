package main.solver.impl;

import main.solver.AStarNode;
import main.solver.AbstractPathfindingAlgorithm;
import main.solver.distanceheuristic.DistanceHeuristic;

public abstract class AbstractPathfindingHeuristicAlgorithm<T extends AStarNode> extends AbstractPathfindingAlgorithm<T> {

    public AbstractPathfindingHeuristicAlgorithm(T start, T goal) {
        super(start,goal);
    }

    protected abstract double getHeuristicCost(T from, T to);
    protected abstract void setHeuristic(DistanceHeuristic<T> heuristic);
}
