package core;

import java.util.List;

/**
 * Created by Omer on 04/01/2018.
 */
public interface Logic {
    List<Point> moveOptions(Cell PlayerColor, Board board);
    boolean makeMove(Cell playerColor, Point pos, Board board);
    boolean isValidPoint(List<Point> points, Point pos);
}
