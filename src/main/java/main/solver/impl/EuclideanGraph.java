package main.solver.impl;

import main.ui.Cell;
import main.ui.Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EuclideanGraph {

    private final int xSize;
    private final int ySize;
    private List<List<Node>> nodes = new ArrayList<>();

    public EuclideanGraph(Grid grid) {
        this.xSize = grid.getColumns();
        this.ySize = grid.getRows();
        createGraph(grid);
        setNodesNeighbors();
    }

    private void setNodesNeighbors() {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Node currentNode = getNodeAtPosition(i, j);
                currentNode.setNeighbors(findNodeNeighbors(i, j));
            }
        }
    }

    private List<Node> findNodeNeighbors(int i, int j) {
        List<Node> neighbors = new ArrayList<>();
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

    private void createGraph(Grid grid) {
        List<List<Cell>> cells = grid.getCells();
        for (int i = 0; i < xSize; i++) {
            nodes.add(i, new ArrayList<>());
            for (int j = 0; j < ySize; j++) {
                nodes.get(i).add(j, new Node(cells.get(i).get(j)));
            }
        }
    }

    public Node getNodeAtPosition(int x, int y) {
        return nodes.get(x).get(y);
    }

    public List<Node> getAllNodes() {
        return nodes.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
