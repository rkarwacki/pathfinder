package main.solver.datastructures;

public interface NodeParentAware<T> {
    T getParentNode();
    void setParentNode(T parent);
}
