package main.solver.impl;

import main.solver.AStarNode;

import java.util.Collections;
import java.util.List;

public class PathfindingResult<T extends AStarNode> {
    private List<T> path = Collections.emptyList();
    private List<T> visitedNodes = Collections.emptyList();

    public List<T> getPath() {
        return path;
    }

    public void setPath(List<T> path) {
        this.path = path;
    }

    public List<T> getVisitedNodes() {
        return visitedNodes;
    }

    public void setVisitedNodes(List<T> visitedNodes) {
        this.visitedNodes = visitedNodes;
    }
}
