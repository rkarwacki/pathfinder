package main.solver.neighborfinding;

import main.solver.datastructures.NodeNeighborhoodAware;

import java.util.List;

@FunctionalInterface
public interface NeighborFindingStrategy <T extends NodeNeighborhoodAware> {
    List<T> getNeighbors(T node);
}
