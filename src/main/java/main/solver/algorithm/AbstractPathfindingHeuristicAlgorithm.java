package main.solver.algorithm;

import main.solver.datastructures.AStarNode;
import main.solver.algorithm.distanceheuristic.DistanceHeuristic;

public abstract class AbstractPathfindingHeuristicAlgorithm<T extends AStarNode> extends AbstractPathfindingAlgorithm<T> {

    public AbstractPathfindingHeuristicAlgorithm(T start, T goal) {
        super(start,goal);
    }

    protected abstract double getHeuristicCost(T from, T to);
    protected abstract void setHeuristic(DistanceHeuristic<T> heuristic);
}
