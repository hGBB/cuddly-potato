/**
 *
 */
public class Cell {
    private boolean alive;
    private int column;
    private int row;
    private int neighbors;

    /**
     * Common constructor for new Cells.
     *
     * @param alive  Boolean, wheater the cell is alive.
     * @param column Int, the Cell's X Coordinate.
     * @param row    Int, the Cell's Y Coordinate.
     */
    public Cell(boolean alive, int column, int row) {
        this.alive = alive;
        this.row = row;
        this.column = column;
    }

    /**
     * Getter method of the cell's x coordinate.
     *
     * @return column the cell is located at as int.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Getter method of the Cell's y coordinate.
     *
     * @return row the Cell is located at as int.
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter method of the Cell's status.
     *
     * @return status of the Cell as boolean.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Setter method of the Cell's status.
     *
     * @param alive Boolean weather a Cell will be set to live or die.
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Getter method of the Cell's neighbors.
     *
     * @return The number of living neighboring Cells.
     */
    public int getNeighbors() {
        return neighbors;
    }

    /**
     * Setter method of the Cell's neighbors.
     *
     * @param neighbors Sets the amount of a Cells neighbors.
     */
    public void setNeighbors(int neighbors) {
        this.neighbors = neighbors;
    }
}
