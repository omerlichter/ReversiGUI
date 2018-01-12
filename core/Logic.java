package core;

import java.util.List;

/**
 * Created by Omer on 04/01/2018.
 */
public interface Logic {
    /**
     * return all the possible moves of the player
     * @param playerColor color of the player
     * @param board board of the game
     * @return list of points pos of moves
     */
    List<Point> moveOptions(Cell playerColor, Board board);

    /**
     * play the move on the board
     * @param playerColor player color
     * @param pos point pos on the move
     * @param board board of the game
     * @return true if succeed, else false
     */
    boolean makeMove(Cell playerColor, Point pos, Board board);

    /**
     * check if the point pos move is valid
     * @param points list of valid point pos moves
     * @param pos point pos move to check
     * @return tru if valid, else false
     */
    boolean isValidPoint(List<Point> points, Point pos);
}
