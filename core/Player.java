package core;

import java.util.List;

/**
 * Created by Omer on 04/01/2018.
 */
public abstract class Player {
    protected Drawer drawer;
    protected Cell playerColor;

    public Player(Drawer drawer, Cell playerColor) {
        this.drawer = drawer;
        this.playerColor = playerColor;
    }

    public Cell getPlayerColor() {
        return this.playerColor;
    }

    public abstract Point chooseMove(List<Point> points, Logic logic, Board board);
}
