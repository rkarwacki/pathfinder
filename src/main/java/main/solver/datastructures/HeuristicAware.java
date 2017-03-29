package main.solver.datastructures;

public interface HeuristicAware extends CostAware {
    double getHeuristicCost();
    void setHeuristicCost(double distance);
}
