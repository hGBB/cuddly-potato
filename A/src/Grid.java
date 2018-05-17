import java.util.Collection;

/**
 * Interface Grid unifies Grid and Cell of the Game of Life
 */
public interface Grid {

    /**
     * Returns weather the Cell at the chosen position on the board is
     * alive.
     *
     * @param col The X-Coordinate of the Cell.
     * @param row The Y-Coordinate of the Cell.
     * @return The status of the Cell.
     */
    boolean isAlive(int col, int row); // get status of cell

    /**
     * Sets the Cell at the chosen position on the board to a given status.
     *
     * @param col The X-Coordinate of the Cell.
     * @param row The Y-Coordinate of the Cell.
     * @param alive The status the Cell will be set to.
     */
    void setAlive(int col, int row, boolean alive);  // set status of cell

    /**
     * Resizes the game board to a given size.
     *
     * @param cols The new width of the game board.
     * @param rows The new height of the game board.
     */
    void resize(int cols, int rows);   // resize grid

    /**
     * Gets the number of Columns of the grid.
     *
     * @return The number of Columns.
     */
    int getColumns();                  // x-dimension

    /**
     * Gets the number of Rows of the grid.
     *
     * @return The number of Rows.
     */
    int getRows();                     // y-dimension

    /**
     * Gets all Cells with the status 'alive'.
     *
     * @return All living Cells.
     */
    Collection<Cell> getPopulation();  // all living cells

    /**
     * Clears the Board. Sets all Cells to status 'dead'.
     */
    void clear();                      // kill all cells

    /**
     * Generates the next generation of living Cells of the Board.
     */
    void next();                       // compute next generation

    /**
     * Gets the number of the current generation.
     *
     * @return The current generation.
     */
    int getGenerations();              // get number of generations

    /**
     * Gets the string representation of the current grid. The zero point is on
     * the top left the "end point" on the bottom right.
     *
     * @return The string representation of the current grid.
     */
    String toString();                 // get string representation
}