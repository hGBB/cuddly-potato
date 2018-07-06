package view.components;

import javax.swing.*;


public class GridCell extends JPanel {
    private boolean alive;
    private int col;
    private int row;

    public GridCell(int col, int row, boolean alive) {
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));

        this.alive = alive;
        this.col = col;
        this.row = row;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
