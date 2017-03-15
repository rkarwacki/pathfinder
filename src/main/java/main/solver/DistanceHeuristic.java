package main.solver;

public interface DistanceHeuristic<T extends Point> {
    double getDistance(T start, T goal);
}
