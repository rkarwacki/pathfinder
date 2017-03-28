package main.solver.algorithm;

import main.solver.datastructures.AStarNode;
import main.solver.datastructures.impl.PathfindingResult;

public interface PathfindingAlgorithm<T extends AStarNode> {
    PathfindingResult<T> solve();
    PathfindingResult<T> reconstructPath(T node);
    void performIteration();
}
