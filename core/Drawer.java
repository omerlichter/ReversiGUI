package core;

import java.util.List;

/**
 * Created by Omer on 04/01/2018.
 */
public interface Drawer {
    /**
     * draw the board
     * @param board board to draw
     */
    void drawBoard(Board board);

    /**
     * draw player move title
     * @param playerColor color of the player
     */
    void drawPlayerMoveTitle(Cell playerColor);

    /**
     * draw possible move
     * @param moves list of the moves
     */
    void drawPossibleMovesTitle(List<Point> moves);

    /**
     * draw player insert dialog
     */
    void drawPlayerInsertDialog();

    /**
     * draw message
     * @param message message to draw
     */
    void drawMessage(String message);

    /**
     * draw end of the game
     * @param playerColor color of the player
     */
    void drawEndOfGame(Cell playerColor);

    /**
     * get move from the player
     * @return point pos of the move
     */
    Point getMove();
}
