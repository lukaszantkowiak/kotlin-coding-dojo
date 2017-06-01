package com.infusion.kcd.view;

import com.infusion.kcd.model.Board;
import com.infusion.kcd.model.State;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CellsBoard extends Application {

    private static CellsBoard instance;

    private GridDisplay gridDisplay;

    private Stage primaryStage;

    public CellsBoard() {
        super();
        instance = this;
    }

    public static synchronized CellsBoard getInstance() {
        if (instance == null) {
            new Thread(() -> Application.launch(CellsBoard.class)).start();
        }
        while (instance == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
        return instance;
    }

    public class GridDisplay {

        private static final double ELEMENT_SIZE = 20;
        private static final double GAP = ELEMENT_SIZE / 10;

        private TilePane tilePane = new TilePane();
        private Group display = new Group(tilePane);
        private int rows;
        private int columns;

        GridDisplay(final Board board) {
            tilePane.setHgap(GAP);
            tilePane.setVgap(GAP);
            setColumns(board.getColumns());
            setRows(board.getRows());
            createElements(board);
        }

        void setColumns(int newColumns) {
            columns = newColumns;
            tilePane.setPrefColumns(columns);
        }

        void setRows(int newRows) {
            rows = newRows;
            tilePane.setPrefRows(rows);
        }

        Group getDisplay() {
            return display;
        }

        private void createElements(Board board) {
            tilePane.getChildren().clear();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    tilePane.getChildren().add(createElement(board.getState(i, j)));
                }
            }
        }

        private Rectangle createElement(final State state) {
            Rectangle rectangle = new Rectangle(ELEMENT_SIZE, ELEMENT_SIZE);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(getCellColor(state));
            rectangle.setArcHeight(10);
            rectangle.setArcWidth(10);

            return rectangle;
        }

        private Color getCellColor(final State state) {
            if (state == State.ALIVE) {
                return Color.BLUE;
            }
            return Color.WHITE;
        }

    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        gridDisplay = new GridDisplay(new Board(1, 1));

        BorderPane mainPanel = new BorderPane();
        mainPanel.setCenter(gridDisplay.getDisplay());

        Scene scene = new Scene(mainPanel, 600, 600);
        primaryStage.setTitle("Game!");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public void updateState(final Board board) {
        Platform.runLater(() -> {
            Scene scene = primaryStage.getScene();
            BorderPane root = (BorderPane) scene.getRoot();
            gridDisplay = new GridDisplay(board);
            root.setCenter(gridDisplay.getDisplay());
        });
    }
}