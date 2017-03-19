package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.solver.distanceheuristic.DistanceHeuristic;
import main.solver.distanceheuristic.impl.EuclideanDistance;
import main.solver.distanceheuristic.impl.ManhattanDistance;
import main.solver.impl.*;
import main.solver.neighborfinding.impl.CornerCuttingIfParallelOpenStrategy;
import main.ui.Cell;
import main.ui.Grid;
import main.ui.MouseGestures;

public class Main extends Application {

    private boolean showHoverCursor = true;

    private static final int COLUMNS = 60;
    private static final int ROWS = 60;
    private static final double WINDOW_WIDTH = 600;
    private static final double WINDOW_HEIGHT = 600;

    private static final int START_NODE_X = 2;
    private static final int START_NODE_Y = 2;
    private static final int GOAL_NODE_X = 40;
    private static final int GOAL_NODE_Y = 40;

    private static final boolean SHOW_EXPLORED_NODES = true;

    private Grid grid;

    @Override
    public void start(Stage primaryStage) {
        MouseGestures mg = new MouseGestures(showHoverCursor);
        try {
            Button button = new Button();
            button.setText("start");
            CheckBox checkBox = new CheckBox("s");
            grid = new Grid(COLUMNS, ROWS, WINDOW_WIDTH, WINDOW_HEIGHT);
            makeGridPaintable(grid, mg);
            BorderPane root = new BorderPane();
            root.setCenter(grid);
            root.setTop(button);
            root.setBottom(checkBox);
            grid.getCell(START_NODE_X, START_NODE_Y).setAsStart();
            grid.getCell(GOAL_NODE_X, GOAL_NODE_Y).setAsGoal();
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
            button.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                grid.removePath();
                grid.removeVisited();
                DistanceHeuristic distanceHeuristic;
                if (checkBox.isSelected())
                distanceHeuristic = new ManhattanDistance();
                else distanceHeuristic = new EuclideanDistance();
                EuclideanGraph euclideanGraph = new EuclideanGraph(grid, new CornerCuttingIfParallelOpenStrategy());
                    Astar astar = new Astar(euclideanGraph, distanceHeuristic, new NodeData(START_NODE_X, START_NODE_Y), new NodeData(GOAL_NODE_X, GOAL_NODE_Y));
                    AstarResult result = astar.solve();
                    grid.setPath(result.getPath());
                    if (SHOW_EXPLORED_NODES) {
                        grid.setVisitedNodes(result.getVisitedNodes());
                    }
            });

        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    private void makeGridPaintable(Grid grid, MouseGestures mg) {
        for (int column = 0; column < COLUMNS; column++) {
            for (int row = 0; row < ROWS; row++) {
                Cell cell = grid.getCell(column, row);
                mg.makePaintable(cell);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}