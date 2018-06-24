package view.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class Grid extends JPanel {
    public Grid(int height, int width) {

        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();


        for (int i = 0; i <= height; i++) {
            for (int j = 0; j <= width; j++) {
                constraints.gridx = j;
                constraints.gridy = i;
                GridCell cellPane = new GridCell();
                Border border = new MatteBorder(1, 1, (i == height ? 1 : 0), (j == width ? 1 : 0), Color.BLACK);

                cellPane.setBorder(border);
                this.add(cellPane, constraints);
            }
        }
    }
}