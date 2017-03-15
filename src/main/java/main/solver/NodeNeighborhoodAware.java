package main.solver;

import java.util.List;

public interface NodeNeighborhoodAware {
    List<AStarNode> getValidNeighbors();
}
