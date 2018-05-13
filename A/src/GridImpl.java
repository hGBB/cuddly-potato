import java.util.Collection;
import java.util.LinkedHashSet;

public class GridImpl implements Grid {
    private int generation;
    private int columns;
    private int rows;
    private Cell[][] grid;

    public GridImpl(int rows, int columns) {
        this.generation = 0;
        this.rows = rows;
        this.columns = columns;
        this.grid = new Cell[columns][rows];
        for (int i = 0; i < rows; i++) {    // TODO: new 3 1 / new 1 3 checken!
            for (int j = 0; j < columns; j++) {
                grid[i][j] = new Cell(false, i, j);
            }
        }
    }

    public GridImpl(int generation, int rows, int columns, Cell[][] grid) {
        this.generation = generation;
        this.rows = rows;
        this.columns = columns;
        this.grid = grid;
    }


    @Override
    public boolean isAlive(int col, int row) {
        return grid[col - 1][row - 1].isAlive();
    }

    @Override
    public void setAlive(int col, int row, boolean alive) {
        grid[col - 1][row - 1].setAlive(alive);
    }

    @Override
    public void resize(int cols, int rows) {
        Collection<Cell> aliveCells = getPopulation();
        this.grid = new Cell[cols][rows];
        this.columns = cols;
        this.rows = rows;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = new Cell(false, i, j);
            }
        }
        for (Cell cell : aliveCells) {
            if (cell.getColumn() < cols && cell.getRow() < rows) {
                grid[cell.getColumn()][cell.getRow()].setAlive(true);
            }
        }
    }

    @Override
    public int getColumns() {
        return columns;
    }

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
                    livingCells.add(cell);
                }
            }

        }
        return livingCells;
    }

    @Override
    public void clear() {
        for (Cell cell : getPopulation()) {
            cell.setAlive(false);
        }
    }

    @Override
    public void next() {
        for (Cell cell : getPopulation()) {
            setNeighbors(cell);
        }
        for (int i = 0; i < columns; i++) {
            for (Cell cell : grid[i]) {
                if (!cell.isAlive() && cell.getNeighbors() == 3) {
                    cell.setAlive(true);
                } else if (cell.isAlive() && !(cell.getNeighbors() == 2
                        || cell.getNeighbors() == 3)) {
                    cell.setAlive(false);
                }
            }

        }
        generation++;
    }

    @Override
    public int getGenerations() {
        return generation;
    }

    @Override
    public String toString() { // TODO: check for n 2 1 and n 1 2 !!!
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (grid[j][i].isAlive()) {
                    result.append("x");
                } else {
                    result.append(".");
                }
            }
            result.append("\n");
        }

        return result.toString();
    }

    private void setNeighbors(Cell cell) {
        for (int i = 0; i < columns; i++) {
            for (Cell allCells : grid[i]) {
                int neighbors = 0;
                for (Cell livingCells : getPopulation()) {
                    if (Math.abs(allCells.getColumn() - livingCells.getColumn())
                            == 1 && Math.abs(allCells.getRow() -
                            livingCells.getRow()) == 1) {
                        neighbors++;
                    } else if (Math.abs(allCells.getColumn() -
                            livingCells.getColumn()) == 1 && allCells.getRow()
                            == livingCells.getRow()) {
                        neighbors++;
                    } else if (allCells.getColumn() == livingCells.getColumn()
                            && Math.abs(allCells.getRow()
                            - livingCells.getRow()) == 1) {
                        neighbors++;
                    }
                }
                allCells.setNeighbors(neighbors);
            }

        }
    }
}
