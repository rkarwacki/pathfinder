package main.solver.algorithm;

import main.solver.datastructures.GraphOrientedNode;
import main.solver.datastructures.impl.PathfindingResult;

public interface PathfindingAlgorithm<T extends GraphOrientedNode<T>> {
    PathfindingResult<T> solve();
    PathfindingResult<T> reconstructPath(T node);
    void performIteration();
}
