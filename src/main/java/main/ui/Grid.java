package main.ui;

import javafx.scene.layout.Pane;
import main.solver.AStarNode;
import main.solver.impl.AStarNodeImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Grid extends Pane {

    private int rows;
    private int columns;

    private double width;
    private double height;

    private List<List<Cell>> cells  = new ArrayList<>();

    public Grid(int columns, int rows, double width, double height) {
        this.columns = columns;
        this.rows = rows;
        this.width = width;
        this.height = height;
        createGrid(columns, rows);
    }

    private void createGrid(int columns, int rows) {
        for (int i = 0; i < columns; i++) {
            ArrayList<Cell> cellList = new ArrayList<>();
            cells.add(cellList);
            for (int j = 0; j < rows; j++) {
                addCellToGridAndInterface(new Cell(i,j));
            }
        }
    }

    public void addCellToGridAndInterface(Cell cell) {
        int column = cell.getColumn();
        int row = cell.getRow();
        cells.get(column).add(row, cell);
        double cellWidth = width / columns;
        double cellHeight = height / rows;
        double xCellPosition = cellWidth * column;
        double yCellPosition = cellHeight * row;
        cell.setLayoutX(xCellPosition);
        cell.setLayoutY(yCellPosition);
        cell.setPrefWidth(cellWidth);
        cell.setPrefHeight(cellHeight);
        getChildren().add(cell);
    }

    public Cell getCell(int column, int row) {
        return cells.get(column).get(row);
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    private List<Cell> flatCellsList() {
        return cells.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void unhighlight() {
        cells.forEach(list -> list.forEach(Cell::unhighlight));
    }

    public void removePath() {
        flatCellsList().stream().forEach(Cell::unsetAsPathPart);
    }

    public void setPath(List<AStarNode> path) {
        for (AStarNode node : path) {
            getCell(node.getXCoordinate(), node.getYCoordinate()).setAsPathPart();
        }
    }
}