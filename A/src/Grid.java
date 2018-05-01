import java.util.Collection;

public interface Grid {
    boolean isAlive(int col, int row); // get status of cell

    void setAlive(int col, int row, boolean alive);  // set status of cell

    void resize(int cols, int rows);   // resize grid

    int getColumns();                  // x-dimension

    int getRows();                     // y-dimension

    Collection<Cell> getPopulation();  // all living cells

    void clear();                      // kill all cells

    void next();                       // compute next generation

    int getGenerations();              // get number of generations

    String toString();                 // get string representation
}