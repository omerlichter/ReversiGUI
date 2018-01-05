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

    public GameFlow(int size, Player blackPlayer, Player whitePlayer, Logic logic, Drawer drawer) {
        this.board = new Board(size);
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
        this.logic = logic;
        this.drawer = drawer;
    }

    public void run() {
        boolean blackPlayerHaveOptions = true;
        boolean whitePlayerHaveOptions = true;
        int numberOfFullCells = 0;
        int numberOfCells = this.board.getSize() * this.board.getSize();
        int turnNumber = 0;

        while ((blackPlayerHaveOptions || whitePlayerHaveOptions) &&
                numberOfFullCells < numberOfCells) {
            // start new turn
            turnNumber++;

            if (turnNumber % 2 == 0) {
                whitePlayerHaveOptions = this.playOneTurn(this.blackPlayer);
            } else {
                blackPlayerHaveOptions = this.playOneTurn(this.whitePlayer);
            }

            // add the number of full cells
            numberOfFullCells = this.board.getNumOfBlackCells() +
                    this.board.getNumOFWhiteCells();
        }

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
    }

    private boolean playOneTurn(Player player) {

        // get all optional moves of the player
        List<Point> moveOptions = this.logic.moveOptions(player.getPlayerColor(), this.board);

        // ask the player to choose move
        Point chosenMove;
        chosenMove = player.chooseMove(moveOptions, this.logic, this.board);

        if (chosenMove == null) {
            return false;
        }

        // play the chosen move
        this.logic.makeMove(player.getPlayerColor(), chosenMove, this.board);
        return true;
    }
}
