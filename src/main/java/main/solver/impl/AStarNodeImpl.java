package main.solver.impl;

import main.solver.AStarNode;
import main.solver.NodeNeighborhoodAware;
import main.solver.State;
import main.ui.Cell;

import java.util.List;
import java.util.stream.Collectors;

public class AStarNodeImpl implements AStarNode, NodeNeighborhoodAware, Comparable<AStarNode> {

    private final int x;
    private final int y;
    private final State state;
    private List<AStarNode> neighbors;
    private double incrementalCost;
    private double heuristicCost;
    private AStarNode parentNode;

    public void setParentNode(AStarNode parentNode) {
        this.parentNode = parentNode;
    }

    public AStarNodeImpl(Cell cell) {
        this(cell.getColumn(), cell.getRow(),
                getState(cell));
    }

    public AStarNodeImpl(int x, int y, State state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }

    private static State getState(Cell cell) {
        State state = State.OPEN;
        if (cell.isHighlighted()) {
            state = State.BLOCKED;
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
    public AStarNode getParent() {
        return parentNode;
    }

    @Override
    public List<AStarNode> getValidNeighbors() {
        return neighbors.stream()
                .filter(node -> !node.getState().equals(State.BLOCKED))
                .collect(Collectors.toList());
    }

    public State getState() {
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
}
