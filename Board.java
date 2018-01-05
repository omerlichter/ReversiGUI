/**
 * Created by Omer on 04/01/2018.
 */
public class Board {
    private Cell board[][];
    private int numOfBlackCells;
    private int numOFWhiteCells;

    public Board(int size) {
        this.board = new Cell[size][size];
        int halfSize = size / 2;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((i == (halfSize - 1) && j == (halfSize - 1)) || (i == halfSize && j == halfSize)) {
                    this.board[i][j] = Cell.WHITE;
                }
                else if (((i == halfSize - 1) && j == halfSize) || (i == halfSize && j == (halfSize - 1))) {
                    this.board[i][j] = Cell.BLACK;
                } else {
                    this.board[i][j] = Cell.EMPTY;
                }
            }
        }
        this.numOfBlackCells = 2;
        this.numOFWhiteCells = 2;
    }

    public int getSize() {
        return this.board.length;
    }

    public int getNumOfBlackCells() {
        return this.numOfBlackCells;
    }

    public int getNumOFWhiteCells() {
        return this.numOFWhiteCells;
    }

    public Cell getCellAt(Point pos) {
        return this.board[pos.getRow()][pos.getColumn()];
    }

    public boolean setCellAt(Point pos, Cell status) {
        int row = pos.getRow();
        int column = pos.getColumn();
        if (row >= 0 && row < this.getSize()
                && column >= 0 && column < this.getSize()) {
            Cell lastCellStatus = this.getCellAt(pos);
            this.board[row][column] = status;
            if (lastCellStatus == Cell.BLACK && status == Cell.WHITE) {
                this.numOFWhiteCells++;
                this.numOfBlackCells--;
            } else if (lastCellStatus == Cell.WHITE && status == Cell.BLACK) {
                this.numOfBlackCells++;
                this.numOFWhiteCells--;
            } else if (lastCellStatus == Cell.EMPTY && status == Cell.WHITE) {
                this.numOFWhiteCells++;
            } else if (lastCellStatus == Cell.EMPTY && status == Cell.BLACK) {
                this.numOfBlackCells++;
            }
            return true;
        }
        return false;
    }
}
