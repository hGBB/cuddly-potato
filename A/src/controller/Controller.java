package controller;

import model.Grid;
import model.GridImpl;
import model.Shape;
import model.ShapeCollection;
import view.Gui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Observable;

@SuppressWarnings("deprecation")
public final class Controller extends Observable {
    private static JFrame frame = new JFrame("Game of Life");
    private static Grid grid = newGame();
    private ShapeCollection shapes = new ShapeCollection();

    public Controller() {
    }

    private static Grid newGame() {
        return new GridImpl();
    }

    public void changeCellStatus(int col, int row, boolean alive) {
        grid.setAlive(col, row, alive);
        this.setChanged();
        this.notifyObservers(grid);
    }

    public void resizeGrid(int col, int row) {
        grid.resize(col, row);
        this.setChanged();
        this.notifyObservers(grid);
    }

    public void startButton() {
        grid.next();
        this.setChanged();
        this.notifyObservers(grid);
    }

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
                        // TODO: error handling! too small of a grid -> error message
                        System.out.println("false!");
                    }
                }
            }
        }
        this.setChanged();
        this.notifyObservers(grid);
    }

    public static void main(String[] args) throws IOException {
        grid = new GridImpl(3, 3);
        frame.setContentPane(new Gui(grid).contentPane);
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(100, 100, 650, 400);
        frame.setMinimumSize(new Dimension(650, 400));
        frame.setVisible(true);
    }
}
