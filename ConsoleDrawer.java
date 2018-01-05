import java.util.List;

/**
 * Created by Omer on 04/01/2018.
 */
public class ConsoleDrawer implements Drawer {
    @Override
    public void drawBoard(Board board) {
        int size = board.getSize();

        // print title
        System.out.println("current board:");

        // print header line
        System.out.print("|");
        for (int i = 0; i < size; i++) {
            System.out.print(" " + (i + 1) + " |");
        }
        System.out.println();
        System.out.print("--");
        for (int i = 0; i < size; ++i) {
            System.out.print("----");
        }
        System.out.println();

        // print every lines
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < size; j++) {
                // print cell status
                switch (board.getCellAt(new Point(i, j))) {
                    case BLACK: System.out.print(" X |");
                        break;
                    case WHITE: System.out.print(" O |");
                        break;
                    case EMPTY: System.out.print("   |");
                        break;
                }
            }
            System.out.println();
            System.out.print("--");
            for (int m = 0; m < size; ++m) {
                System.out.print("----");
            }
            System.out.println();
        }
    }

    @Override
    public void drawEndOfGame(Cell playerColor) {
        System.out.println("End of the game.");
        if (playerColor == Cell.BLACK) {
            System.out.println("Player X win!");
        } else if (playerColor == Cell.WHITE) {
            System.out.println("Player O win!");
        } else {
            System.out.println("Draw!");
        }
    }

    @Override
    public void drawMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void drawPlayerMoveTitle(Cell playerColor) {
        // print on screen the player color
        switch (playerColor) {
            case BLACK: System.out.print("X: ");
                break;
            case WHITE: System.out.print("O: ");
                break;
        }
        System.out.print("It's your move.");
    }

    @Override
    public void drawPossibleMovesTitle(List<Point> moves) {
        // print on screen the options
        System.out.print("Your possible moves: ");
        for (int i = 0; i < moves.size(); i++) {
            Point point = moves.get(i);
            System.out.print("(" + point.getRow() + ", " + point.getColumn() + ") ");
        }
        System.out.println();
        System.out.println();
    }

    @Override
    public void drawPlayerInsertDialog() {
        System.out.println("Please enter your move row,col: ");
    }
}
