package core;

import java.util.List;

/**
 * Created by Omer on 04/01/2018.
 */
public interface Drawer {
    void drawBoard(Board board);
    void drawPlayerMoveTitle(Cell playerColor);
    void drawPossibleMovesTitle(List<Point> moves);
    void drawPlayerInsertDialog();
    void drawMessage(String message);
    void drawEndOfGame(Cell playerColor);
    Point getMove();
}
