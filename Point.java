/**
 * Created by Omer on 04/01/2018.
 */
public class Point {
    int row;
    int column;

    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean equals(Point other) {
        if (this.row == other.getRow() && this.column == other.getColumn()) {
            return true;
        }
        return false;
    }
}
