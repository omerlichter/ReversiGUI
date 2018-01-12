package core;

/**
 * Created by Omer on 04/01/2018.
 */
public class Point {
    int row;
    int column;

    /**
     * constructor
     * @param row row of the point
     * @param column column of the point
     */
    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * get function
     * @return row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * grt function
     * @return column
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * set function
     * @param row row of the point
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * set function
     * @param column column of the point
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * check if two point are equals
     * @param other point to check
     * @return true if they equal, else false
     */
    public boolean equals(Point other) {
        if (this.row == other.getRow() && this.column == other.getColumn()) {
            return true;
        }
        return false;
    }
}
