package core;

import java.util.List;

/**
 * Created by Omer on 04/01/2018.
 */
public class GameFlow {
    private Player blackPlayer;
    private Player whitePlayer;
    private Board board;
    private Logic logic;
    private Drawer drawer;

    private int turnNumber;

    public GameFlow(int size, Drawer drawer) {
        this.drawer = drawer;
        this.board = new Board(size);
        this.blackPlayer = new LocalPlayer(drawer, Cell.BLACK);
        this.whitePlayer =  new LocalPlayer(drawer, Cell.WHITE);
        this.logic = new ReversiLogic();
    }

    public boolean runOneTurn() {
        boolean blackPlayerHaveOptions = true;
        boolean whitePlayerHaveOptions = true;
        int numberOfFullCells = this.board.getNumOfBlackCells() + this.board.getNumOFWhiteCells();
        int numberOfCells = this.board.getSize() * this.board.getSize();

        if ((!blackPlayerHaveOptions && !whitePlayerHaveOptions) || numberOfFullCells >= numberOfCells) {
            // print the board on the screen
            this.drawer.drawBoard(this.board);

            // check who win
            if (this.board.getNumOfBlackCells() > this.board.getNumOFWhiteCells()) {
                this.drawer.drawEndOfGame(Cell.BLACK);
            } else if (this.board.getNumOFWhiteCells() > this.board.getNumOfBlackCells()) {
                this.drawer.drawEndOfGame(Cell.WHITE);
            } else {
                this.drawer.drawEndOfGame(Cell.EMPTY);
            }
            return false;
        }

        if (this.turnNumber % 2 == 0) {
            whitePlayerHaveOptions = this.playOneTurn(this.blackPlayer);
        } else {
            blackPlayerHaveOptions = this.playOneTurn(this.whitePlayer);
        }

        // print the board on the screen
        this.drawer.drawBoard(board);
        return true;
    }

    private boolean playOneTurn(Player player) {

        // get all optional moves of the player
        List<Point> moveOptions = this.logic.moveOptions(player.getPlayerColor(), this.board);

        // ask the player to choose move
        Point chosenMove;
        chosenMove = player.chooseMove(moveOptions, this.logic, this.board);

        if (chosenMove == null) {
            this.turnNumber++;
            return false;
        }

        if (chosenMove.equals(new Point(-1, -1))) {
            return false;
        }

        // play the chosen move
        this.logic.makeMove(player.getPlayerColor(), chosenMove, this.board);
        this.turnNumber++;
        return true;
    }

    public Board getBoard() {
        return this.board;
    }
}
