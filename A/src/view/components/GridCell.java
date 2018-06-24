package view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GridCell extends JPanel {
    private Color cellColorSmall = Color.decode("#551A8B");
    private Color cellColorMedium = Color.decode("#000080");
    private Color cellColorLarge = Color.decode("#FFD700");

    volatile private boolean mousePressed;

    public GridCell() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                setBackground(cellColorLarge);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePressed = false;
            }

            @Override
            public void mouseEntered(MouseEvent event) {
                if (mousePressed) {
                    setBackground(cellColorSmall);
                }
            }

            @Override
            public void mouseExited(MouseEvent event) {
                if (mousePressed) {
                    setBackground(cellColorSmall);
                }
            }

        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(20, 20);
    }
}
