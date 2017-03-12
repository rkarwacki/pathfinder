package main.ui;

import javafx.scene.layout.StackPane;

public class Cell extends StackPane {

    private final int column;
    private final int row;

    private boolean isHighlighted;
    private boolean isStart;

    public boolean isStart() {
        return isStart;
    }

    public boolean isGoal() {
        return isGoal;
    }

    private boolean isGoal;

    public Cell(int column, int row) {
        this.column = column;
        this.row = row;
        getStyleClass().add("cell");
        setOpacity(0.9);
    }

    public void setAsStart() {
        if (isHighlighted()) unhighlight();
        getStyleClass().remove("cell-start");
        getStyleClass().add("cell-start");
        isStart = true;
    }

    public void unsetAsStart() {
        getStyleClass().remove("cell-start");
        isStart = false;
    }

    public void setAsGoal() {
        getStyleClass().remove("cell-goal");
        getStyleClass().add("cell-goal");
        isGoal = true;
    }

    public void unsetAsGoal() {
        getStyleClass().remove("cell-goal");
        isGoal = false;
    }

    public void highlight() {
        getStyleClass().remove("cell-highlight");
        getStyleClass().add("cell-highlight");
        isHighlighted = true;
    }

    public void unhighlight() {
        getStyleClass().remove("cell-highlight");
        isHighlighted = false;
    }

    public void hoverHighlight() {
        // ensure the style is only once in the style list
        getStyleClass().remove("cell-hover-highlight");
        // addCellToGridAndInterface style
        getStyleClass().add("cell-hover-highlight");
    }

    public void hoverUnhighlight() {
        getStyleClass().remove("cell-hover-highlight");
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public String toString() {
        return this.column + "/" + this.row;
    }

    public boolean isHighlighted() {
        return isHighlighted;
    }

    public void setAsPathPart() {
        getStyleClass().add("cell-path");
    }
}