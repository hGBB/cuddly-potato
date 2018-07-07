package view.components;

import javax.swing.*;
import java.awt.*;

/**
 * An own JPanel implementation to represent the cells of the game of life.
 */
public class GridCell extends JPanel {
    private boolean alive;
    private int col;
    private int row;

    /**
     * Constructor
     *
     * @param col the X Position on the grid.
     * @param row the Y Position on the grid.
     * @param alive the status of the cell.
     */
    public GridCell(int col, int row, boolean alive) {
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        this.setBackground(Color.white);
        this.alive = alive;
        this.col = col;
        this.row = row;
    }

    /**
     * Getter method.
     *
     * @return the status of the cell.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Setter method.
     *
     * @param alive sets the status of the cell.
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Getter method.
     *
     * @return the X Position of the cell.
     */
    public int getCol() {
        return col;
    }

    /**
     * Getter method.
     *
     * @return the Y Position of the cell.
     */
    public int getRow() {
        return row;
    }
}
