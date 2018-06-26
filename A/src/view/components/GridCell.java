package view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GridCell extends JPanel {
    private Color cellColorSmall = Color.decode("#551A8B");
    private Color cellColorMedium = Color.decode("#000080");
    private Color cellColorLarge = Color.decode("#FFD700");

    private int size;

    volatile private boolean mousePressed;

    public GridCell(boolean alive) {
        if (alive) {
            setBackground(cellColorLarge);
        }
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                setBackground(cellColorSmall);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePressed = true;
                initThread();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePressed = false;
            }


        });
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(size, size);
    }

    volatile private boolean isRunning = false;
    private synchronized boolean checkAndMark() {
        if (isRunning) return false;
        isRunning = true;
        return true;
    }

    private void initThread() {
        if (checkAndMark()) {
            new Thread(() -> {

                    setBackground(Color.red);
            }).start();
        }
    }
}
