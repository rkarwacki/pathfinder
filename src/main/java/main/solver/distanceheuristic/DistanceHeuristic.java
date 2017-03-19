package main.solver.distanceheuristic;

import main.solver.Point;

@FunctionalInterface
public interface DistanceHeuristic<T extends Point> {
    double getDistance(T start, T goal);
}
