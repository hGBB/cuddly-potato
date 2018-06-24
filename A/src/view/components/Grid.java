package view.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class Grid extends JPanel {
    public Grid(int height, int width) {

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();


        for (int row = 0; row <= height; row++) {
            for (int col = 0; col <= width; col++) {
                gbc.gridx = col;
                gbc.gridy = row;

                GridCell cellPane = new GridCell();
                Border border = new MatteBorder(1, 1, (row == height ? 1 : 0), (col == width ? 1 : 0), Color.GRAY);

                cellPane.setBorder(border);
                this.add(cellPane, gbc);
            }
        }
    }
}