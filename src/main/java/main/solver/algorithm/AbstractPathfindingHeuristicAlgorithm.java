package main.solver.algorithm;

import main.solver.datastructures.AStarNode;
import main.solver.algorithm.distanceheuristic.DistanceHeuristic;
import main.solver.datastructures.GraphOrientedNode;

public abstract class AbstractPathfindingHeuristicAlgorithm<T extends GraphOrientedNode<T>> extends AbstractPathfindingAlgorithm<T> {
    public AbstractPathfindingHeuristicAlgorithm(T start, T goal) {
        super(start,goal);
    }
    protected abstract double getHeuristicCost(T from, T to);
    protected abstract void setHeuristic(DistanceHeuristic heuristic);
}
