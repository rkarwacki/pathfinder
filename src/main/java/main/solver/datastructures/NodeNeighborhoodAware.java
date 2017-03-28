package main.solver.datastructures;

import java.util.List;

public interface NodeNeighborhoodAware {
    List<AStarNode> getNeighbors();
}
