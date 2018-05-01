public class Cell {
    private boolean alive;
    private int column;
    private int row;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Cell(boolean alive, int column, int row) {
        this.alive = alive;
        this.row = row;
        this.column = column;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
