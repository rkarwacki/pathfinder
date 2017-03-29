package main.solver.datastructures.impl;

import main.solver.datastructures.AStarNode;
import main.solver.neighborfinding.NeighborFindingStrategy;
import main.solver.datastructures.NodeState;
import main.ui.Cell;

import java.util.List;

public class HeuristicNodeImpl implements AStarNode, Comparable<AStarNode> {

    private final int x;
    private final int y;
    private final NodeState state;
    private List<AStarNode> neighbors;
    private double incrementalCost;
    private double heuristicCost;
    private AStarNode parentNode;
    private NeighborFindingStrategy<AStarNode> neighborFindingStrategy;

    public void setParentNode(AStarNode parentNode) {
        this.parentNode = parentNode;
    }

    public HeuristicNodeImpl(Cell cell, NeighborFindingStrategy<AStarNode> neighborFindingStrategy) {
        this(cell.getColumn(), cell.getRow(),
                getState(cell), neighborFindingStrategy);
    }

    public HeuristicNodeImpl(int x, int y, NodeState state, NeighborFindingStrategy<AStarNode> neighborFindingStrategy) {
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

    @Override
    public Integer getXCoordinate() {
        return x;
    }

    @Override
    public Integer getYCoordinate() {
        return y;
    }

    @Override
    public double getIncrementalCost() {
        return incrementalCost;
    }

    @Override
    public double getHeuristicCost() {
        return heuristicCost;
    }

    @Override
    public double getTotalCost() {
        return getHeuristicCost() + getIncrementalCost();
    }

    @Override
    public AStarNode getParentNode() {
        return parentNode;
    }

    @Override
    public List<AStarNode> getNeighbors() {
        return neighbors;
    }

    @Override
    public List<AStarNode> getValidNeighbors() {
        return neighborFindingStrategy.getNeighbors(this);
    }

    public NodeState getState() {
        return state;
    }

    public void setNeighbors(List<AStarNode> neighbors) {
        this.neighbors = neighbors;
    }

    public void setIncrementalCost(double incrementalCost) {
        this.incrementalCost = incrementalCost;
    }

    public void setHeuristicCost(double heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    @Override
    public int compareTo(AStarNode o) {
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

        HeuristicNodeImpl aStarNode = (HeuristicNodeImpl) o;

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
