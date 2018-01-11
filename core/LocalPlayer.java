package core;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Omer on 04/01/2018.
 */
public class LocalPlayer extends Player {

    public LocalPlayer(Drawer drawer, Cell playerColor) {
        super(drawer, playerColor);
    }

    @Override
    public Point chooseMove(List<Point> points, Logic logic, Board board) {

        Point move;

        if (points.size() == 0) {
            drawer.drawMessage("No possible moves. Play passes back to the other player.");
            return null;
        }

        // draw
        this.drawer.drawPlayerMoveTitle(this.playerColor);
        this.drawer.drawPossibleMovesTitle(points);

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
            this.drawer.drawMessage("not Valid core.Point");
            return new Point(-1, -1);
        }

        return move;
    }
}
