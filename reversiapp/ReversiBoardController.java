package reversiapp;

import core.Board;
import core.Cell;
import core.Point;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.io.IOException;

/**
 * Created by Omer on 10/01/2018.
 */
public class ReversiBoardController extends GridPane{
    private Board board;

    public ReversiBoardController(Board board) {
        this.board = board;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void draw() {
        this.getChildren().clear();

        int height = (int)this.getPrefHeight();
        int width = (int)this.getPrefWidth();

        int cellHeight = height / board.getSize();
        int cellWidth = width / board.getSize();

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < this.board.getSize(); j++) {
                if (board.getCellAt(new Point(j, i)) == Cell.EMPTY) {
                    Rectangle cell = new Rectangle(cellWidth, cellHeight, Color.WHITE);
                    cell.setStroke(Color.BLACK);
                    this.add(cell, j, i);
                }
                else if (board.getCellAt(new Point(j, i)) == Cell.BLACK) {
                    StackPane stackPane = new StackPane();
                    Rectangle cell = new Rectangle(cellWidth, cellHeight, Color.WHITE);
                    cell.setStroke(Color.BLACK);
                    stackPane.getChildren().add(cell);
                    double circleRadius = (cellHeight > cellWidth) ? cellWidth : cellHeight;
                    Circle blackCircle = new Circle(0, 0, (circleRadius - 10) / 2, Color.BLACK);
                    blackCircle.setStroke(Color.BLACK);
                    stackPane.getChildren().add(blackCircle);
                    this.add(stackPane, j, i);
                }
                else {
                    StackPane stackPane = new StackPane();
                    Rectangle cell = new Rectangle(cellWidth, cellHeight, Color.WHITE);
                    cell.setStroke(Color.BLACK);
                    stackPane.getChildren().add(cell);
                    double circleRadius = (cellHeight > cellWidth) ? cellWidth : cellHeight;
                    Circle whiteCircle = new Circle(0, 0, (circleRadius - 10) / 2, Color.WHITE);
                    whiteCircle.setStroke(Color.BLACK);
                    stackPane.getChildren().add(whiteCircle);
                    this.add(stackPane, j, i);
                }
            }
        }
    }

    public Board getBoard() {
        return this.board;
    }
}
