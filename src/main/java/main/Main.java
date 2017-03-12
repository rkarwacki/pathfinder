package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.solver.impl.Astar;
import main.solver.impl.EuclideanGraph;
import main.solver.impl.Node;
import main.ui.Cell;
import main.ui.Grid;
import main.ui.MouseGestures;

import java.util.List;

public class Main extends Application {

    boolean showHoverCursor = true;

    private int columns = 30;
    private int rows = 30;
    private double width = 800;
    private double height = 800;
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
            grid.getCell(6, 18).setAsStart();
            grid.getCell(columns - 4, rows - 12).setAsGoal();

            Scene scene = new Scene(root, width, height);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
            button.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                Astar astar = new Astar();
                EuclideanGraph euclideanGraph = new EuclideanGraph(grid);
                List<Node> solve = astar.solve(euclideanGraph);
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