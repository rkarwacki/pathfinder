package main.solver;

import main.solver.impl.PathfindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public abstract class AbstractPathfindingAlgorithm<T extends AStarNode> implements PathfindingAlgorithm<T>  {
    protected T startNode;
    protected T goalNode;
    protected T currentNode;
    private PathfindingResult<T> result = new PathfindingResult<>();

    protected Queue<T> unexploredNodes = new PriorityQueue<>();
    protected List<T> exploredNodes = new ArrayList<>();

    public AbstractPathfindingAlgorithm(T startNode, T goalNode) {
        this.startNode = startNode;
        this.goalNode = goalNode;
    }

    @Override
    public PathfindingResult<T> reconstructPath(T node) {
        List<T> path = new ArrayList<>();
        while (node.getParent() != null) {
            path.add(node);
            node = (T) node.getParent();
        }
        result.setPath(path);
        result.setVisitedNodes(new ArrayList<>(exploredNodes));
        return result;
    }

    @Override
    public PathfindingResult<T> solve() {
        while (!loopTerminationCondition()) {
            currentNode = unexploredNodes.poll();
            if (pathFound()) {
                return reconstructPath(currentNode);
            }
            performIteration();
        }
        return result;
    }

    public boolean pathFound() {
        return currentNode == goalNode;
    }

    protected boolean loopTerminationCondition() {
        return unexploredNodes.isEmpty();
    }
}
