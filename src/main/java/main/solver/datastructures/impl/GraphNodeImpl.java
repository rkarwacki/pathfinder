package main.solver.datastructures.impl;

import main.solver.datastructures.NodeState;
import main.solver.neighborfinding.NeighborFindingStrategy;
import main.ui.Cell;

import java.util.List;

public class GraphNodeImpl implements GraphNode, Comparable<GraphNode>{
    private final int x;
    private final int y;
    private final NodeState state;
    private List<GraphNode> neighbors;
    private double incrementalCost;
    private GraphNode parentNode;
    private NeighborFindingStrategy<GraphNode> neighborFindingStrategy;

    public void setParentNode(GraphNode parentNode) {
        this.parentNode = parentNode;
    }

    public GraphNodeImpl(Cell cell, NeighborFindingStrategy<GraphNode> neighborFindingStrategy) {
        this(cell.getColumn(), cell.getRow(),
                getState(cell), neighborFindingStrategy);
    }

    public GraphNodeImpl(int x, int y, NodeState state, NeighborFindingStrategy<GraphNode> neighborFindingStrategy) {
        this.x = x;
        this.y = y;
        this.state = state;
        this.neighborFindingStrategy = neighborFindingStrategy;
    }

    private static NodeState getState(Cell cell) {
        NodeState state = NodeState.OPEN;
        if (cell.isHighlighted()) {
            state = NodeState.BLOCKED;
        }
        return state;
    }

    public Integer getXCoordinate() {
        return x;
    }

    public Integer getYCoordinate() {
        return y;
    }

    public double getIncrementalCost() {
        return incrementalCost;
    }

    public double getTotalCost() {
        return getIncrementalCost();
    }

    public GraphNode getParentNode() {
        return parentNode;
    }

    public List<GraphNode> getNeighbors() {
        return neighbors;
    }

    public List<GraphNode> getValidNeighbors() {
        return neighborFindingStrategy.getNeighbors(this);
    }

    public NodeState getState() {
        return state;
    }

    public void setNeighbors(List<GraphNode> neighbors) {
        this.neighbors = neighbors;
    }

    public void setIncrementalCost(double incrementalCost) {
        this.incrementalCost = incrementalCost;
    }

    @Override
    public int compareTo(GraphNode o) {
        if (this.getTotalCost() > o.getTotalCost()) {
            return 1;
        } else if (this.getTotalCost() < o.getTotalCost()) {
            return -1;
        } else return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GraphNodeImpl aStarNode = (GraphNodeImpl) o;

        return x == aStarNode.x && y == aStarNode.y && state == aStarNode.state;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HeuristicNodeImpl{" +
                "x=" + x +
                ", y=" + y +
                ", state=" + state +
                '}';
    }
}
