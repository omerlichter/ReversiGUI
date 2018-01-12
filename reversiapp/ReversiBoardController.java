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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Omer on 10/01/2018.
 */
public class ReversiBoardController extends GridPane{
    private Board board;
    private Color playerOColor;
    private Color playerXColor;
    private List<Point> possibleMoves;

    /**
     * constructor
     * @param board board of the game
     */
    public ReversiBoardController(Board board) {
        this.possibleMoves = new ArrayList<>();
        this.board = board;
        this.playerOColor = Color.WHITE;
        this.playerXColor = Color.BLACK;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * draw the gui board
     */
    public void draw() {
        this.getChildren().clear();

        int height = (int)this.getPrefHeight();
        int width = (int)this.getPrefWidth();

        int cellHeight = height / board.getSize();
        int cellWidth = width / board.getSize();

        // draw all cells
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < this.board.getSize(); j++) {
                if (board.getCellAt(new Point(j, i)) == Cell.EMPTY) {
                    Rectangle cell = new Rectangle(cellWidth, cellHeight, Color.color(1, 0.98, 0.92));
                    cell.setStroke(Color.BLACK);
                    this.add(cell, j, i);
                }
                else if (board.getCellAt(new Point(j, i)) == Cell.BLACK) {
                    StackPane stackPane = new StackPane();
                    Rectangle cell = new Rectangle(cellWidth, cellHeight,  Color.color(1, 0.98, 0.92));
                    cell.setStroke(Color.BLACK);
                    stackPane.getChildren().add(cell);
                    double circleRadius = (cellHeight > cellWidth) ? cellWidth : cellHeight;
                    Circle blackCircle = new Circle(0, 0, (circleRadius - 10) / 2, this.playerXColor);
                    blackCircle.setStroke(Color.BLACK);
                    stackPane.getChildren().add(blackCircle);
                    this.add(stackPane, j, i);
                }
                else {
                    StackPane stackPane = new StackPane();
                    Rectangle cell = new Rectangle(cellWidth, cellHeight,  Color.color(1, 0.98, 0.92));
                    cell.setStroke(Color.BLACK);
                    stackPane.getChildren().add(cell);
                    double circleRadius = (cellHeight > cellWidth) ? cellWidth : cellHeight;
                    Circle whiteCircle = new Circle(0, 0, (circleRadius - 10) / 2, this.playerOColor);
                    whiteCircle.setStroke(Color.BLACK);
                    stackPane.getChildren().add(whiteCircle);
                    this.add(stackPane, j, i);
                }
            }
        }

        // draw possible moves cells
        Iterator<Point> it = this.possibleMoves.iterator();
        while (it.hasNext()) {
            Point point = it.next();
            int posX = point.getColumn() - 1;
            int posY = point.getRow() - 1;

            StackPane stackPane = new StackPane();
            Rectangle cell = new Rectangle(cellWidth - 1, cellHeight - 1, Color.color(0.8, 0.8, 0.8));
            stackPane.getChildren().add(cell);
            this.add(stackPane, posY, posX);
        }
    }

    /**
     * get function
     * @return board
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * set function
     * @param possibleMoves list of point pos moves
     */
    public void setPossibleMoves(List<Point> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    /**
     * set function
     * @param color color of O player
     */
    public void setPlayerOColor(Color color) {
        this.playerOColor = color;
    }

    /**
     * set function
     * @param color color of X player
     */
    public void setPlayerXColor(Color color) {
        this.playerXColor = color;
    }
}
