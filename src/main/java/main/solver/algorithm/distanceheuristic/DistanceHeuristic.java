package main.solver.algorithm.distanceheuristic;

import main.solver.datastructures.Point;

@FunctionalInterface
public interface DistanceHeuristic<T extends Point> {
    double getDistance(T start, T goal);
}
