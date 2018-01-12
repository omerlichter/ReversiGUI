package core;

import java.util.List;

/**
 * Created by Omer on 04/01/2018.
 */
public abstract class Player {
    protected Drawer drawer;
    protected Cell playerColor;

    /**
     * constructor
     * @param drawer drawer
     * @param playerColor player color
     */
    public Player(Drawer drawer, Cell playerColor) {
        this.drawer = drawer;
        this.playerColor = playerColor;
    }

    /**
     * get function
     * @return color of the player
     */
    public Cell getPlayerColor() {
        return this.playerColor;
    }

    /**
     * choose move, abstract
     * @param points list of points
     * @param logic logic of the game
     * @param board board
     * @return point pos move
     */
    public abstract Point chooseMove(List<Point> points, Logic logic, Board board);
}
