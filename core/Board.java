package core;

/**
 * Created by Omer on 04/01/2018.
 */
public class Board {
    private Cell board[][];
    private int numOfBlackCells;
    private int numOFWhiteCells;

    /**
     * constructor
     * @param size size of the board
     */
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

    /**
     * get function
     * @return size of the board
     */
    public int getSize() {
        return this.board.length;
    }

    /**
     * get function
     * @return num of black cells
     */
    public int getNumOfBlackCells() {
        return this.numOfBlackCells;
    }

    /**
     * gte function
     * @return num of white cells
     */
    public int getNumOFWhiteCells() {
        return this.numOFWhiteCells;
    }

    /**
     * return the cell status in position
     * @param pos row and column of the cell
     * @return cell status
     */
    public Cell getCellAt(Point pos) {
        return this.board[pos.getRow()][pos.getColumn()];
    }

    /**
     * set atatus to cell, update num of black and white cells
     * @param pos row and column of the cell
     * @param status the satatus to update
     * @return if succeed
     */
    public boolean setCellAt(Point pos, Cell status) {
        int row = pos.getRow();
        int column = pos.getColumn();
        if (row >= 0 && row < this.getSize()
                && column >= 0 && column < this.getSize()) {
            Cell lastCellStatus = this.getCellAt(pos);
            this.board[row][column] = status;

            // update num of black and white cells
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
