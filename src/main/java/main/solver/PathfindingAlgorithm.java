package main.solver;

import main.solver.impl.PathfindingResult;

public interface PathfindingAlgorithm<T extends AStarNode> {
    PathfindingResult<T> solve();
    PathfindingResult<T> reconstructPath(T node);
    void performIteration();
}
