package view.components;

import javax.swing.*;
import java.awt.*;


public class GridCell extends JPanel {
    private Color cellColorSmall = Color.decode("#551A8B");
    private Color cellColorMedium = Color.decode("#000080");
    private Color cellColorLarge = Color.decode("#FFD700");
    private int xPosition;
    private int yPosition;

    public GridCell(boolean alive, int yPosition, int xPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        if (alive) {
            setBackground(cellColorSmall);
        }
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }
}
