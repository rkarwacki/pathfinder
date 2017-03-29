package main.solver.datastructures;

import java.util.List;

public interface NodeNeighborhoodAware<T> {
    List<T> getNeighbors();
    List<T> getValidNeighbors();
    void setNeighbors(List<T> nodeNeighbors);
}
