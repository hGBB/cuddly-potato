package gol;

/**
 * This class is the core element of the Game of Life.
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
     * @param column Int, the gol.Cell's X Coordinate.
     * @param row    Int, the gol.Cell's Y Coordinate.
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
     * Getter method of the gol.Cell's y coordinate.
     *
     * @return row the gol.Cell is located at as int.
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter method of the gol.Cell's status.
     *
     * @return status of the gol.Cell as boolean.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Setter method of the gol.Cell's status.
     *
     * @param alive Boolean weather a gol.Cell will be set to live or die.
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Getter method of the gol.Cell's neighbors.
     *
     * @return The number of living neighboring Cells.
     */
    public int getNeighbors() {
        return neighbors;
    }

    /**
     * Setter method of the gol.Cell's neighbors.
     *
     * @param neighbors Sets the amount of a Cells neighbors.
     */
    public void setNeighbors(int neighbors) {
        this.neighbors = neighbors;
    }

    @Override
    public String toString() {
        if (isAlive()) {
            return "X";
        } else {
            return ".";
        }
    }
}
