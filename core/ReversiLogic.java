package core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 04/01/2018.
 */
public class ReversiLogic implements Logic {
    @Override
    public List<Point> moveOptions(Cell playerColor, Board board) {
        List<Point> points = new ArrayList<Point>();

        // go over all the cells in the matrix
        for(int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {

                boolean isValid = false;
                Cell cellStatus = board.getCellAt(new Point(i, j));

                // if the cell status is empty
                if (cellStatus == Cell.EMPTY) {

                    // go over all the neighbors of the cell
                    for (int u = -1; u < 2; u++) {
                        for (int r = -1; r < 2; r++) {
                            // without the cell itself
                            if (u != 0 || r != 0) {

                                if ((((i + u) >= 0 && (i + u) < board.getSize())
                                        && ((j + r) >= 0 && (j + r) < board.getSize()))
                                        && board.getCellAt(new Point(i + u, j + r)) != Cell.EMPTY
                                        && board.getCellAt(new Point(i + u, j + r)) != playerColor) {
                                    // mult value
                                    int m = 2;
                                    while ((((i + u * m) >= 0 && (i + u * m) < board.getSize())
                                            && ((j + r * m) >= 0 && (j + r * m) < board.getSize()))) {
                                        // get the next neighbors in the path
                                        Cell neighbor = board.getCellAt(new Point((i + u * m), (j + r * m)));
                                        if (neighbor != playerColor) {
                                            if (neighbor == Cell.EMPTY)
                                                break;
                                            m++;
                                        } else if (neighbor == playerColor) {
                                            isValid = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (isValid == true) {
                        Point point = new Point(i + 1, j + 1);
                        points.add(point);
                    }
                }
            }
        }
        return points;
    }

    @Override
    public boolean makeMove(Cell playerColor, Point pos, Board board) {
        int row = pos.getRow() - 1;
        int column = pos.getColumn() - 1;

        boolean succeed = board.setCellAt(new Point(row, column), playerColor);
        if (succeed == false) {
            return false;
        }

        // go over all the neighbors of the cell
        for (int u = -1; u < 2; u++) {
            for (int r = -1; r < 2; r++) {
                // without the cell itself
                if (u != 0 || r != 0) {

                    if ((((row + u) >= 0 && (row + u) < board.getSize())
                            && ((column + r) >= 0 && (column + r) < board.getSize()))
                            && board.getCellAt(new Point(row + u, column + r)) != Cell.EMPTY
                            && board.getCellAt(new Point(row + u, column + r)) != playerColor) {
                        // mult value
                        int m = 2;
                        boolean isValid = false;
                        while ((((row + u * m) >= 0 && (row + u * m) < board.getSize())
                                && ((column + r * m) >= 0 && (column + r * m) < board.getSize()))) {
                            // get the next neighbors in the path
                            Cell neighbor = board.getCellAt(new Point((row + u * m), (column + r * m)));
                            if (neighbor != playerColor) {
                                if (neighbor == Cell.EMPTY)
                                    break;
                                m++;
                            } else if (neighbor == playerColor) {
                                isValid = true;
                                break;
                            }
                        }

                        if (isValid == true) {
                            for (int i = 0; i < m; i++) {
                                board.setCellAt(new Point((row + u * i), (column + r * i)), playerColor);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean isValidPoint(List<Point> points, Point pos) {
        for (int i = 0; i < points.size(); i++) {
            if (pos.equals(points.get(i))) {
                return true;
            }
        }
        return false;
    }
}
