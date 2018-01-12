package core;

import java.util.List;

/**
 * Created by Omer on 04/01/2018.
 */
public class GameFlow {
    private Player secondPlayer;
    private Player firstPlayer;
    private Board board;
    private Logic logic;
    private Drawer drawer;

    private boolean firstPlayerHaveOptions;
    private boolean secondPlayerHaveOptions;
    private int turnNumber;
    private boolean gameRunning;

    /**
     * constructor
     * @param size sie of the board
     * @param drawer drawer
     * @param firstPlayer first player
     * @param secondPlayer second player
     */
    public GameFlow(int size, Drawer drawer, Player firstPlayer, Player secondPlayer) {
        this.drawer = drawer;
        this.board = new Board(size);
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.logic = new ReversiLogic();
        this.firstPlayerHaveOptions = true;
        this.secondPlayerHaveOptions = true;
        this.turnNumber = 0;
        this.gameRunning = true;
    }

    /**
     * initialize the game, draw the first player possible moves
     */
    public void initializeGame() {
        // get all optional moves of the player
        List<Point> moveOptions = this.logic.moveOptions(firstPlayer.getPlayerColor(), this.board);
        drawer.drawPlayerMoveTitle(firstPlayer.getPlayerColor());
        drawer.drawPossibleMovesTitle(moveOptions);
        drawer.drawBoard(this.board);
    }

    /**
     * run one turn of the game
     * @return if the game end return false, else return true
     */
    public boolean runOneTurn() {

        // check if the game is end
        if (!this.gameRunning) {
            return false;
        }

        // check player turn
        if (this.turnNumber % 2 == 0) {
            try {
                this.firstPlayerHaveOptions = this.playOneTurn(this.firstPlayer);
                this.secondPlayerHaveOptions = this.nextPlayerTurnInit(this.secondPlayer);
            } catch (Exception e) {
                drawer.drawMessage(e.getMessage());
            }
        } else {
            try {
                this.secondPlayerHaveOptions = this.playOneTurn(this.secondPlayer);
                this.firstPlayerHaveOptions = this.nextPlayerTurnInit(this.firstPlayer);
            } catch (Exception e) {
                drawer.drawMessage(e.getMessage());
            }
        }

        // print the board on the screen
        this.drawer.drawBoard(board);

        int numberOfFullCells = this.board.getNumOfBlackCells() + this.board.getNumOFWhiteCells();
        int numberOfCells = this.board.getSize() * this.board.getSize();

        // check if the game end in this turn...
        if ((!this.secondPlayerHaveOptions && !this.firstPlayerHaveOptions) || numberOfFullCells >= numberOfCells) {

            this.gameRunning = false;

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
        return true;
    }

    /**
     * play one turn of specific player.
     * @param player player to play turn.
     * @return true if the player played, return false if the player not have possible moves.
     * @throws Exception if the move is not valid.
     */
    private boolean playOneTurn(Player player) throws Exception {

        // get all optional moves of the player
        List<Point> moveOptions = this.logic.moveOptions(player.getPlayerColor(), this.board);

        // ask the player to choose move
        Point chosenMove;
        chosenMove = player.chooseMove(moveOptions, this.logic, this.board);

        // if the player not have possible moves
        if (chosenMove == null) {
            this.turnNumber++;
            return false;
        }

        // if the move not valid
        if (chosenMove.equals(new Point(-1, -1))) {
            throw new Exception("not valid point");
        }

        // play the chosen move
        this.logic.makeMove(player.getPlayerColor(), chosenMove, this.board);
        this.turnNumber++;

        return true;
    }

    /**
     * draw the next player possible move
     * @param player next player turn
     * @return false if player not have possible moves
     */
    private boolean nextPlayerTurnInit(Player player) {
        // get all optional moves of the player
        List<Point> moveOptions = this.logic.moveOptions(player.getPlayerColor(), this.board);
        drawer.drawPlayerMoveTitle(player.getPlayerColor());
        drawer.drawPossibleMovesTitle(moveOptions);

        if (moveOptions.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * gte function
     * @return the board
     */
    public Board getBoard() {
        return this.board;
    }
}
