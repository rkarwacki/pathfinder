package main.solver.datastructures.impl;

import main.solver.datastructures.AStarNode;
import main.solver.neighborfinding.NeighborFindingStrategy;
import main.ui.Cell;
import main.ui.Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EuclideanGraph {

    private final int xSize;
    private final int ySize;
    private final List<List<AStarNode>> nodes;

    public EuclideanGraph(List<List<AStarNode>> nodes, NeighborFindingStrategy<AStarNode> nodeNeighborFindingStrategy){
        this.xSize = nodes.size();
        this.ySize = nodes.get(0).size();
        this.nodes = nodes;
        setNodesNeighbors();
    }

    public EuclideanGraph(Grid grid, NeighborFindingStrategy<AStarNode> nodeNeighborFindingStrategy) {
        nodes = new ArrayList<>();
        this.xSize = grid.getColumns();
        this.ySize = grid.getRows();
        createGraph(grid, nodeNeighborFindingStrategy);
        setNodesNeighbors();
    }

    private void setNodesNeighbors() {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                AStarNode currentNode = getNodeAtPosition(i, j);
                currentNode.setNeighbors(findNodeNeighbors(i, j));
            }
        }
    }

    private List<AStarNode> findNodeNeighbors(int i, int j) {
        List<AStarNode> neighbors = new ArrayList<>();
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                if (isNotCurrentNode(k, l) && isWithinGraphBounds(i, j, k, l)) {
                    neighbors.add(getNodeAtPosition(i + k, j + l));
                }
            }
        }
        return neighbors;
    }

    private boolean isWithinGraphBounds(int i, int j, int k, int l) {
        return i + k >= 0 && i + k < xSize && j + l >= 0 && j + l < ySize;
    }

    private boolean isNotCurrentNode(int k, int l) {
        return !(k == 0 && l == 0);
    }

    private void createGraph(Grid grid, NeighborFindingStrategy<AStarNode> neighborFindingStrategy) {
        List<List<Cell>> cells = grid.getCells();
        for (int i = 0; i < xSize; i++) {
            nodes.add(i, new ArrayList<>());
            for (int j = 0; j < ySize; j++) {
                nodes.get(i).add(j, new AStarNodeImpl(cells.get(i).get(j), neighborFindingStrategy));
            }
        }
    }

    public AStarNode getNodeAtPosition(int x, int y) {
        return nodes.get(x).get(y);
    }

    public List<AStarNode> getAllNodes() {
        return nodes.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
