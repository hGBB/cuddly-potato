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
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("deprecation")
public final class Controller extends Observable {
    private static boolean run;
    private static Timer timer;
    private Toolkit toolkit;
    private Gui gui;
    private static Grid grid;
    private int threadSpeed;
    private ShapeCollection shapes = new ShapeCollection();

    public void startButton() {
        run = true;
        grid.next();


        System.out.println(grid);
        this.setChanged();
        this.notifyObservers();
    }

    public void stopButton() {
        run = false;
        this.setChanged();
        this.notifyObservers();
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
        this.notifyObservers();
    }

    public void threadComboBox(int speed) {
        threadSpeed = speed;
        this.setChanged();
        this.notifyObservers();
    }

    private class Run extends TimerTask {
        public Run() {
        }

        @Override
        public void run() {
            grid.next();
        }
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Game of Life");
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
