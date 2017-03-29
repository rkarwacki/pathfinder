package main.solver.datastructures.impl;

import main.solver.datastructures.GraphOrientedNode;
import main.solver.datastructures.NodeState;
import main.solver.datastructures.Point;

import java.util.List;

public interface GraphNode extends GraphOrientedNode<GraphNode> {
    double getIncrementalCost();

    double getTotalCost();

    GraphNode getParentNode();

    List<GraphNode> getNeighbors();

    void setIncrementalCost(double value);

    List<GraphNode> getValidNeighbors();

    void setParentNode(GraphNode current);

    void setNeighbors(List<GraphNode> nodeNeighbors);
}
