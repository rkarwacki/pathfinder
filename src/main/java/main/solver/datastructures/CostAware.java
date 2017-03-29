package main.solver.datastructures;

public interface CostAware {
    double getIncrementalCost();
    double getTotalCost();
    void setIncrementalCost(double value);
}
