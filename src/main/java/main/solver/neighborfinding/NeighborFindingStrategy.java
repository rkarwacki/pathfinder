package main.solver.neighborfinding;

import main.solver.datastructures.GraphOrientedNode;

import java.util.List;

@FunctionalInterface
public interface NeighborFindingStrategy <T extends GraphOrientedNode<T>> {
    List<T> getNeighbors(T node);
}
