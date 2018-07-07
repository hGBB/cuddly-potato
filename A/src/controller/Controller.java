package controller;

import model.Grid;
import model.GridImpl;
import model.Shape;
import model.ShapeCollection;
import view.Gui;


import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Color;
import java.util.Observable;

/**
 * The controller of the game of life, handles the interaction between view and
 * model.
 */
@SuppressWarnings("deprecation")
public final class Controller extends Observable {
    private static JFrame frame = new JFrame("Game of Life");
    private static Grid grid;
    private ShapeCollection shapes = new ShapeCollection();

    /**
     * Standard constructor.
     */
    public Controller() {
    }

    /**
     * Changes the status of a cell.
     *
     * @param col The X Position of the cell.
     * @param row The Y Position of the cell.
     * @param alive The new status of the cell.
     */
    public void changeCellStatus(int col, int row, boolean alive) {
        grid.setAlive(col, row, alive);
        this.setChanged();
        this.notifyObservers(grid);
    }

    /**
     * Resizes the grid size of the game board.
     *
     * @param col The new X Dimension.
     * @param row The new Y Dimension.
     */
    public void resizeGrid(int col, int row) {
        grid.resize(col, row);
        this.setChanged();
        this.notifyObservers(grid);
    }

    /**
     * Calculates a new Generation.
     */
    public void startButton() {
        grid.next();
        this.setChanged();
        this.notifyObservers(grid);
    }

    /**
     * Calls a Shape on the Grid.
     *
     * @param string The name of the shape.
     */
    public void shapeComboBox(String string) {
        grid.clear();
        if (!string.equals("Clear")) {
            for (Shape sh : shapes.getShapeCollection()) {
                if (string.equals(sh.getName())) {
                    if (grid.getRows() >= sh.getShapeRows()
                            && grid.getColumns() >= sh.getShapeColumns()) {
                        for (int[] coords : sh.getCoordinates()) {
                            int gameX = (grid.getColumns()
                                    - sh.getShapeColumns())
                                    / 2 + coords[0];
                            int gameY = (grid.getRows() - sh.getShapeRows())
                                    / 2 + coords[1];
                            grid.setAlive(gameX, gameY, true);
                        }
                    } else {
                        System.out.println("false!");
                    }
                }
            }
        }
        this.setChanged();
        this.notifyObservers(grid);
    }

    /**
     * The main method of the game of life.
     *
     * @param args program arguments.
     */
    public static void main(String[] args) {
        grid = new GridImpl(36, 16);
        frame.setContentPane(new Gui(grid).getContentPane());
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(100, 100, 650, 400);
        frame.setMinimumSize(new Dimension(650, 400));
        frame.setVisible(true);
    }
}
