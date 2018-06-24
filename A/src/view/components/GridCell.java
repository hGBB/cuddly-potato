package view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridCell extends JPanel {
    private Color defaultBackground = Color.decode("#551A8B");
    private Color cellColor = Color.decode("#551A8B");

    public GridCell() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(cellColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defaultBackground);
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(20, 20);
    }
}
