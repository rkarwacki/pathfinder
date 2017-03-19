package main.solver.neighborfinding.impl;

import main.solver.AStarNode;
import main.solver.neighborfinding.NeighborFindingStrategy;
import main.solver.State;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CornerCuttingIfParallelOpenStrategy implements NeighborFindingStrategy<AStarNode>{
    @Override
    public List<AStarNode> getNeighbors(AStarNode node) {
        return node.getNeighbors().stream()
                .filter(neighbor -> neighborReachable(node, neighbor))
                .filter(neighbor -> !neighbor.getState().equals(State.BLOCKED))
                .collect(Collectors.toList());
    }


    private boolean neighborReachable(AStarNode current, AStarNode neighbor) {
        if (current.getYCoordinate().equals(neighbor.getYCoordinate()) || current.getXCoordinate().equals(neighbor.getXCoordinate())) {
            return true;
        } else {
            boolean neighborInUpperCorner = current.getYCoordinate() > neighbor.getYCoordinate();
            boolean neighborInRightCorner = current.getXCoordinate() < neighbor.getXCoordinate();
            Optional<AStarNode> yNeighbor = current.getNeighbors().stream()
                    .filter(node -> node.getXCoordinate().equals(current.getXCoordinate()))
                    .filter(node -> node.getYCoordinate().equals(
                            neighborInUpperCorner
                                    ? current.getYCoordinate() - 1
                                    : current.getYCoordinate() + 1))
                    .findFirst();

            Optional<AStarNode> xNeighbor = current.getNeighbors().stream()
                    .filter(node -> node.getYCoordinate().equals(current.getYCoordinate()))
                    .filter(node -> node.getXCoordinate().equals(
                            neighborInRightCorner
                                    ? current.getXCoordinate() + 1
                                    : current.getXCoordinate() - 1))
                    .findFirst();

            return xNeighbor.isPresent() && yNeighbor.isPresent() &&
             xNeighbor.get().getState().equals(State.OPEN) && yNeighbor.get().getState().equals(State.OPEN);
        }
    }
}
