package view.components;

import javax.swing.*;
import java.awt.*;


public class GridCell extends JPanel {
    private Color cellColorSmall = Color.decode("#551A8B");
    private Color cellColorMedium = Color.decode("#000080");
    private Color cellColorLarge = Color.decode("#FFD700");

    public GridCell(boolean alive) {
        if (alive) {
            setBackground(cellColorSmall);
        }
    }

}
