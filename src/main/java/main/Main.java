package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.solver.AStarNode;
import main.solver.impl.*;
import main.ui.Cell;
import main.ui.Grid;
import main.ui.MouseGestures;

import java.util.List;

public class Main extends Application {

    boolean showHoverCursor = true;

    private int columns = 100;
    private int rows = 100;
    private double width = 900;
    private double height = 900;
    private Grid grid;

    @Override
    public void start(Stage primaryStage) {
        MouseGestures mg = new MouseGestures(showHoverCursor);
        try {
            Button button = new Button();
            button.setText("start");
            grid = new Grid(columns, rows, width, height);
            makeGridPaintable(grid, mg);
            StackPane root = new StackPane();
            root.getChildren().addAll(grid);
            root.getChildren().addAll(button);
            int startNodeX = 23;
            int startNodeY = 54;
            int goalNodeX = 83;
            int goalNodeY = 87;

            grid.getCell(startNodeX, startNodeY).setAsStart();
            grid.getCell(goalNodeX,goalNodeY).setAsGoal();
            Scene scene = new Scene(root, width, height);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

            button.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                grid.removePath();
                EuclideanGraph euclideanGraph = new EuclideanGraph(grid);
                Astar astar = new Astar(euclideanGraph, new EuclideanDistance(), new NodeData(startNodeX,startNodeY), new NodeData(goalNodeX,goalNodeY));
                List<AStarNode> solve = astar.solve();
                grid.setPath(solve);
            });

        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    private void makeGridPaintable(Grid grid, MouseGestures mg) {
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                Cell cell = grid.getCell(column, row);
                mg.makePaintable(cell);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}