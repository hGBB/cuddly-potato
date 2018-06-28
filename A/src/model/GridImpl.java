package model;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * This is the grid or game board of the game of live. It contains a two
 * dimensional array of cells, the number of the current generation and the
 * max width and height of the grid.
 */
public class GridImpl implements Grid {
    private int generation;
    private int columns;
    private int rows;
    private Cell[][] grid;
    private LinkedHashSet<Cell> population;
    private static final int SURVIVE = 2;
    private static final int SET_ALIVE = 3;

    public GridImpl() {
    }

    /**
     * A Constructor for a gridImpl.
     *
     * @param columns number of columns of the new grid.
     * @param rows    number of rows of the new grid.
     */
    public GridImpl(int columns, int rows) {
        this.generation = 0;
        this.columns = columns;
        this.rows = rows;
        this.grid = new Cell[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                grid[i][j] = new Cell(false, i, j);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive(int col, int row) {
        return grid[col][row].isAlive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAlive(int col, int row, boolean alive) {
        grid[col][row].setAlive(alive);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resize(int cols, int rows) {
        Collection<Cell> aliveCells = getPopulation();
        this.grid = new Cell[cols][rows];
        this.columns = cols;
        this.rows = rows;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[j][i] = new Cell(false, i, j);
            }
        }
        for (Cell cell : aliveCells) {
            if (cell.getColumn() < cols && cell.getRow() < rows) {
                grid[cell.getColumn()][cell.getRow()].setAlive(true);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumns() {
        return columns;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public Collection<Cell> getPopulation() {
        Collection<Cell> livingCells = new LinkedHashSet<>();
        for (int i = 0; i < columns; i++) {
            for (Cell cell : grid[i]) {
                if (cell.isAlive()) {
                    livingCells.add(new Cell(true, cell.getColumn(),
                            cell.getRow()));
                }
            }
        }
        return livingCells;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        for (int i = 0; i < columns; i++) {
            for (Cell cell : grid[i]) {
                cell.setAlive(false);
            }
        }
        generation = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void next() {
        setNeighbors();
        for (int i = 0; i < columns; i++) {
            for (Cell cell : grid[i]) {
                if (!cell.isAlive() && cell.getNeighbors() == SET_ALIVE) {
                    cell.setAlive(true);
                } else if (cell.isAlive() && !(cell.getNeighbors() == SURVIVE
                        || cell.getNeighbors() == SET_ALIVE)) {
                    cell.setAlive(false);
                }
            }
        }
        generation++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getGenerations() {
        return generation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < columns; i++) {
                result.append(grid[i][j]);
            }
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * {@inheritDoc}
     */
    private void setNeighbors() {
        for (int i = 0; i < columns; i++) {
            for (Cell allCells : grid[i]) {
                int proximityCell = 0;
                for (Cell livingCells : getPopulation()) {
                    if (Math.abs(allCells.getColumn() - livingCells.getColumn())
                            == 1 && Math.abs(allCells.getRow()
                            - livingCells.getRow()) == 1) {
                        proximityCell++;
                    } else if (Math.abs(allCells.getColumn()
                            - livingCells.getColumn()) == 1 && allCells.getRow()
                            == livingCells.getRow()) {
                        proximityCell++;
                    } else if (allCells.getColumn() == livingCells.getColumn()
                            && Math.abs(allCells.getRow()
                            - livingCells.getRow()) == 1) {
                        proximityCell++;
                    }
                }
                allCells.setNeighbors(proximityCell);
            }
        }
    }
}
