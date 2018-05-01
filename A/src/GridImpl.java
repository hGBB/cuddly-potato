import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class GridImpl implements Grid {
    private int generation;
    private int rows;
    private int columns;
    private Set<Cell> population;

    public GridImpl(int rows, int columns) {
        this.generation = 1;
        this.rows = rows;
        this.columns = columns;
        this.population = new LinkedHashSet<>();
    }

    public GridImpl(int generation, int rows, int columns, Set<Cell> population) {
        this.generation = generation;
        this.rows = rows;
        this.columns = columns;
        this.population = population;
    }


    @Override
    public boolean isAlive(int col, int row) {
        return false;
    }

    @Override
    public void setAlive(int col, int row, boolean alive) {

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
        return population;
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
}
