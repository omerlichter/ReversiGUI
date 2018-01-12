package core;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Omer on 04/01/2018.
 */
public class LocalPlayer extends Player {

    /**
     * constructor
     * @param drawer drawer
     * @param playerColor player color
     */
    public LocalPlayer(Drawer drawer, Cell playerColor) {
        super(drawer, playerColor);
    }

    /**
     * choose move, and return it
     * @param points list of moves
     * @param logic logic of the game
     * @param board board of the game
     * @return the move
     */
    @Override
    public Point chooseMove(List<Point> points, Logic logic, Board board) {

        Point move;

        if (points.size() == 0) {
            return null;
        }

        boolean validPoint;

        // print
        this.drawer.drawPlayerInsertDialog();

        // get move from the user
        move = this.drawer.getMove();

        // check validation
        if (move != null) {
            validPoint = logic.isValidPoint(points, move);
        } else {
            validPoint = false;
        }

        if (validPoint == false) {
            this.drawer.drawMessage("not Valid Point");
            return new Point(-1, -1);
        }

        return move;
    }
}
