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

    public Grid getGrid() {
        return Controller.grid;
    }

    public void startButton() {
        grid.next();
        System.out.println(grid);
        this.setChanged();
        this.notifyObservers(grid);
    }

    public void shapeComboBox(String string) {
        grid.clear();
        if (!string.equals("Clear")) {
            for (Shape shape : shapes.getShapeCollection()) {
                if (string.equals(shape.getName())) {
                    for (int[] coords : shape.getCoordinates()) {
                        int gameX = (grid.getColumns() - shape.getShapeColumns())
                                / 2 + coords[0];
                        int gameY = (grid.getRows() - shape.getShapeRows())
                                / 2 + coords[1];
                        grid.setAlive(gameX, gameY, true);
                    }
                }
            }
        }
        System.out.println(grid);
        this.setChanged();
        this.notifyObservers(grid);
    }

    public static void main(String[] args) throws IOException {
        grid = new GridImpl(10, 10);
        frame.setContentPane(new Gui(grid).contentPane);
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(100, 100, 650, 400);
        frame.setMinimumSize(new Dimension(450, 200));
        frame.setVisible(true);
    }
}
