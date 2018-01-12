package core;

import reversiapp.ReversiBoardController;
import reversiapp.ReversiGameController;

import java.util.List;

/**
 * Created by Omer on 10/01/2018.
 */
public class GUIDrawer implements Drawer {

    private ReversiGameController reversiGameController;

    /**
     * constructor
     * @param reversiGameController reversi game controller of the gui
     */
    public GUIDrawer(ReversiGameController reversiGameController) {
        this.reversiGameController = reversiGameController;
    }

    /**
     * draw the board
     * @param board board to draw
     */
    @Override
    public void drawBoard(Board board) {
        this.reversiGameController.draw();
    }

    /**
     * draw player move title
     * @param playerColor color of the player
     */
    @Override
    public void drawEndOfGame(Cell playerColor) {
        switch (playerColor) {
            case WHITE:
                this.reversiGameController.endGame("White Player Win!");
                break;
            case BLACK:
                this.reversiGameController.endGame("Black Player Win!");
                break;
            default:
                this.reversiGameController.endGame("Its A Draw!");
                break;
        }
    }

    /**
     * draw message
     * @param message message to draw
     */
    @Override
    public void drawMessage(String message) {
        this.reversiGameController.setMessage(message);
    }

    /**
     * draw player move title
     * @param playerColor color of the player
     */
    @Override
    public void drawPlayerMoveTitle(Cell playerColor) {
        this.reversiGameController.setCurrentPlayer(playerColor);
    }

    /**
     * draw possible moves
     * @param moves list of the moves
     */
    @Override
    public void drawPossibleMovesTitle(List<Point> moves) {
        if (moves.size() == 0) {
            this.reversiGameController.setMessage("No possible moves\nClick on Screen");
        }
        this.reversiGameController.getReversiBoardController().setPossibleMoves(moves);
    }

    /**
     * draw player insert dialog
     */
    @Override
    public void drawPlayerInsertDialog() {

    }

    /**
     * get move from the user
     * @return point pos move
     */
    @Override
    public Point getMove() {
        double mouseX = this.reversiGameController.getMouseX();
        double mouseY = this.reversiGameController.getMouseY();
        ReversiBoardController reversiBoardController = this.reversiGameController.getReversiBoardController();
        int posX = (int) ((mouseX / reversiBoardController.getWidth()) * reversiBoardController.getBoard().getSize()) + 1;
        int posY = (int) ((mouseY / reversiBoardController.getHeight()) * reversiBoardController.getBoard().getSize()) + 1;

        return new Point(posX, posY);
    }
}
