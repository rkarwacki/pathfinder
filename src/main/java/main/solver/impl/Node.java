package main.solver.impl;

import main.solver.AStarNode;
import main.solver.NodeNeighborhoodAware;
import main.solver.State;
import main.ui.Cell;

import java.util.List;

public class Node implements AStarNode, NodeNeighborhoodAware, Comparable<Node> {

    private final int x;
    private final int y;
    private final State state;
    private List<Node> neighbors;
    private double incrementalCost;
    private double heuristicCost;

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    private Node parentNode;

    public Node(Cell cell) {
        this(cell.getColumn(), cell.getRow(),
                getState(cell));
    }

    public Node(int x, int y, State state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }

    private static State getState(Cell cell) {
        State state = State.OPEN;
        if (cell.isHighlighted()) {
            state = State.BLOCKED;
        } else if (cell.isGoal()) {
            state = State.GOAL;
        } else if (cell.isStart()) {
            state = State.START;
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
        if (parentNode == null) return 0;
        return parentNode.getIncrementalCost() + GraphHelper.getCostToNeighborNode(this, parentNode);
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
    public Node getParent() {
        return parentNode;
    }

    @Override
    public List<Node> getNeighbors() {
        return neighbors;
    }

    public State getState() {
        return state;
    }

    public void setNeighbors(List<Node> neighbors) {
        this.neighbors = neighbors;
    }

    public void setIncrementalCost(double incrementalCost) {
        this.incrementalCost = incrementalCost;
    }

    public void setHeuristicCost(double heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    @Override
    public int compareTo(Node o) {
        if (this.getTotalCost() > o.getTotalCost()) {
            return 1;
        } else if (this.getTotalCost() < o.getTotalCost()) {
            return -1;
        } else return 0;
    }
}
