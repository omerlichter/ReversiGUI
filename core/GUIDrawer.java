package core;

import reversiapp.ReversiBoardController;
import reversiapp.ReversiGameController;

import java.util.List;

/**
 * Created by Omer on 10/01/2018.
 */
public class GUIDrawer implements Drawer {

    private ReversiGameController reversiGameController;

    public GUIDrawer(ReversiGameController reversiGameController) {
        this.reversiGameController = reversiGameController;
    }

    @Override
    public void drawBoard(Board board) {
        this.reversiGameController.draw();
    }

    @Override
    public void drawEndOfGame(Cell playerColor) {

    }

    @Override
    public void drawMessage(String message) {

    }

    @Override
    public void drawPlayerMoveTitle(Cell playerColor) {

    }

    @Override
    public void drawPossibleMovesTitle(List<Point> moves) {

    }

    @Override
    public void drawPlayerInsertDialog() {

    }

    @Override
    public Point getMove() {
        double mouseX = this.reversiGameController.getMouseX();
        double mouseY = this.reversiGameController.getMouseY();
        ReversiBoardController reversiBoardController = this.reversiGameController.getReversiBoardController();
        int posX = (int) ((mouseX / reversiBoardController.getWidth()) * reversiBoardController.getBoard().getSize()) + 1;
        int posY = (int) ((mouseY / reversiBoardController.getHeight()) * reversiBoardController.getBoard().getSize()) + 1;


        System.out.println("X: " + posX);
        System.out.println("Y: " + posY);

        return new Point(posX, posY);
    }
}
