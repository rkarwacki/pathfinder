package main.solver;

import java.util.List;

public interface AStarNode extends Point<Integer>, Comparable<AStarNode> {
    double getIncrementalCost();
    double getHeuristicCost();
    double getTotalCost();
    AStarNode getParent();

    void setIncrementalCost(double value);

    void setHeuristicCost(double distance);

    List<AStarNode> getValidNeighbors();

    void setParentNode(AStarNode current);

    State getState();
}
