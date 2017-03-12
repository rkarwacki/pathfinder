package main.solver;

import main.solver.impl.Node;

import java.util.List;

public interface NodeNeighborhoodAware {
    List<Node> getNeighbors();
}
