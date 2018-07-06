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
    private boolean[][] grid;
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
        this.grid = new boolean[columns][rows];
        population = new LinkedHashSet<>();
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                grid[i][j] = false;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive(int col, int row) {
        return grid[col][row];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAlive(int col, int row, boolean alive) {
        if (alive) {
            grid[col][row] = true;
            population.add(new Cell(true, col, row));
        } else {
            grid[col][row] = false;
            for (Cell c : population) {
                if (c.getColumn() == col && c.getRow() == row) {
                    population.remove(c);
                    break;
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resize(int column, int rows) {
        Collection<Cell> aliveCells = getPopulation();
        for (Cell cell : aliveCells) {
            if (cell.getRow() > rows || cell.getColumn() > column) {
                population.remove(cell);
            }
        }
        this.grid = new boolean[column][rows];
        this.columns = column;
        this.rows = rows;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[j][i] = false;
            }
        }
        for (Cell cell : aliveCells) {
            if (cell.getColumn() < column && cell.getRow() < rows) {
                grid[cell.getColumn()][cell.getRow()] = true;
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
        // in order to keep the class secret copy the collection.
        Collection<Cell> livingCells = new LinkedHashSet<>();
        for (Cell c : population) {
            livingCells.add(new Cell(true, c.getColumn(), c.getRow()));
        }
        return livingCells;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        population.clear();
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                grid[i][j] = false;
            }
        }
        generation = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void next() {
        int[][] neighborhood = setNeighbors();
        for (int i = 0; i < neighborhood.length; i++) {
            for (int j = 0; j < neighborhood[i].length; j++) {
                if (!grid[i][j] && neighborhood[i][j] == SET_ALIVE) {
                    setAlive(i, j, true);
                } else if (grid[i][j] && (neighborhood[i][j] < SURVIVE || neighborhood[i][j] > SET_ALIVE)) {
                    grid[i][j] = false;
                    setAlive(i, j, false);
                }
            }
        }

        for (Cell cell : population) {
            if (!cell.isAlive() && cell.getNeighbors() == SET_ALIVE) {
                cell.setAlive(true);
            } else if (cell.isAlive() && !(cell.getNeighbors() == SURVIVE
                    || cell.getNeighbors() == SET_ALIVE)) {
                cell.setAlive(false);
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
                if (grid[i][j]) {
                    result.append("x");
                } else {
                    result.append(".");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * {@inheritDoc}
     */
    private int[][] setNeighbors() {
        int[][] neighborhood = new int[getColumns()][getRows()];
        for (Cell cell : getPopulation()) {
            for (int i = cell.getColumn() - 1; i <= cell.getColumn() + 1; i++) {
                for (int j = cell.getRow() - 1; j <= cell.getRow() + 1; j++) {
                    if (i >= 0 && j >= 0 && i < getColumns() && j < getRows()) {
                        if (!(i == cell.getColumn() && j == cell.getRow())) {
                            neighborhood[i][j]++;
                        }
                    }
                }
            }
        }
        return neighborhood;
    }
}
