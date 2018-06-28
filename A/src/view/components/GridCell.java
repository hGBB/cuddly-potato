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
    private int xPosition;
    private int yPosition;

    public GridCell(boolean alive, int yPosition, int xPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        if (alive) {
            setBackground(cellColorLarge);
        }
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(size, size);
    }

}
