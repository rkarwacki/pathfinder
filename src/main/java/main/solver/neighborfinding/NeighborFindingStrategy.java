package main.solver.neighborfinding;

import main.solver.NodeNeighborhoodAware;

import java.util.List;

@FunctionalInterface
public interface NeighborFindingStrategy <T extends NodeNeighborhoodAware> {
    List<T> getNeighbors(T node);
}
