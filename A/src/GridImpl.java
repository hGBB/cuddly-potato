import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class GridImpl implements Grid {
    private int generation;
    private int columns;
    private int rows;
    private Set<Cell> population;

    public GridImpl(int rows, int columns) {
        this.generation = 1;
        this.rows = rows;
        this.columns = columns;
        this.population = new LinkedHashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                population.add(new Cell(false, i, j));
            }
        }
    }

    public GridImpl(int generation, int rows, int columns, Set<Cell> population) {
        this.generation = generation;
        this.rows = rows;
        this.columns = columns;
        this.population = population;
    }


    @Override
    public boolean isAlive(int col, int row) {
        for (Cell cell : population) {
            if (cell.getColumn() == (col - 1) && cell.getRow() == (row - 1)) {
                return cell.isAlive();
            }
        }
        //TODO: check für ne schönere lösung
        return false;
    }

    @Override
    public void setAlive(int col, int row, boolean alive) {
        for (Cell next : population) {
            if (next.getRow() == (row - 1) && next.getColumn() == (col - 1)) { // TODO: keep an eye on this -1!
                next.setAlive(!next.isAlive()); // change state of cell
                break;
            }
        }
    }

    @Override
    public void resize(int cols, int rows) {
        this.columns = cols;
        this.rows = rows;
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
        for (Cell cell : population) {
            if (cell.isAlive()) {
                livingCells.add(cell);
            }
        }
        return livingCells;
    }

    @Override
    public void clear() {
        this.population.clear();
    }

    @Override
    public void next() {

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
                    for (Cell cell : population) {
                        if (cell.getRow() == i && cell.getColumn() == j) {
                            if (cell.isAlive()) {
                                result.append("x");
                            } else {
                                result.append(".");
                            }
                        }
                    }


                }
            result.append("\n");
        }

        return result.toString();
    }

    private int getNeighbors(Cell cell) {
        int xAxis = cell.getColumn();
        int yAxis = cell.getRow();
        int neighbors = 0;
        for (int i = xAxis - 1; i < xAxis + 1; i++) {
            for (int j = yAxis - 1; j < yAxis + 1; j++) {
                if (xAxis < 0 || xAxis > columns || yAxis < 0 || yAxis > rows) {
                    neighbors++;
                } else {
                    for (Cell pop : population) {
                        if (pop.getColumn() == i && pop.getRow() == j) {
                            neighbors++;
                        }
                    }
                }
            }
        }
        return neighbors;
    }
}
