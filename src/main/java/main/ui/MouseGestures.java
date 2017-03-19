package main.ui;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class MouseGestures {

    private boolean showHoverCursor;

    public MouseGestures(boolean showHoverCursor) {
        this.showHoverCursor = showHoverCursor;
    }

    public void makePaintable(Node node) {
        if (showHoverCursor) {
            node.hoverProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue) {
                    ((Cell) node).hoverHighlight();
                } else {
                    ((Cell) node).hoverUnhighlight();
                }
            });
        }
        node.setOnMousePressed(onMousePressedEventHandler);
        node.setOnDragDetected(onDragDetectedEventHandler);
        node.setOnMouseDragEntered(onMouseDragEnteredEventHandler);
    }

    private EventHandler<MouseEvent> onMousePressedEventHandler = event -> {
        Cell cell = (Cell) event.getSource();
        if (event.isPrimaryButtonDown()) {
            cell.highlight();
        } else if (event.isSecondaryButtonDown()) {
            cell.unhighlight();
        }
    };

    private EventHandler<MouseEvent> onDragDetectedEventHandler = event -> {
        Cell cell = (Cell) event.getSource();
        cell.startFullDrag();
    };
    private EventHandler<MouseEvent> onMouseDragEnteredEventHandler = event -> {
        Cell cell = (Cell) event.getSource();
        if (event.isPrimaryButtonDown()) {
            cell.highlight();
        } else if (event.isSecondaryButtonDown()) {
            cell.unhighlight();
        }
    };
}
