package main.solver.algorithm.distanceheuristic;

import main.solver.datastructures.AStarNode;

public interface DistanceHeuristic {
    double getDistance(AStarNode start, AStarNode goal);
}
