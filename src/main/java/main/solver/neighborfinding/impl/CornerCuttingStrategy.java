package main.solver.neighborfinding.impl;

import main.solver.datastructures.AStarNode;
import main.solver.neighborfinding.NeighborFindingStrategy;
import main.solver.datastructures.NodeState;

import java.util.List;
import java.util.stream.Collectors;

public class CornerCuttingStrategy implements NeighborFindingStrategy<AStarNode> {
    @Override
    public List<AStarNode> getNeighbors(AStarNode node) {
        return node.getNeighbors()
                .stream()
                .filter(neighbor -> !neighbor.getState().equals(NodeState.BLOCKED))
                .collect(Collectors.toList());
    }
}
