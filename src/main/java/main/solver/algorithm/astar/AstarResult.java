package main.solver.impl;

import main.solver.AStarNode;

import java.util.Collections;
import java.util.List;

public class AstarResult {
    private List<AStarNode> path = Collections.emptyList();
    private List<AStarNode> visitedNodes = Collections.emptyList();

    public List<AStarNode> getPath() {
        return path;
    }

    public void setPath(List<AStarNode> path) {
        this.path = path;
    }

    public List<AStarNode> getVisitedNodes() {
        return visitedNodes;
    }

    public void setVisitedNodes(List<AStarNode> visitedNodes) {
        this.visitedNodes = visitedNodes;
    }
}
