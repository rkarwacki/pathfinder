package main.solver;

import main.solver.impl.Node;

public interface AStarNode extends Point<Integer> {
    double getIncrementalCost();
    double getHeuristicCost();
    double getTotalCost();
    Node getParent();
}
