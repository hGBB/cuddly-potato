package graphicalUserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class gridGUI extends JPanel {
    private List<JPanel> jPanelList = new ArrayList<>();

    MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent event) {
            JPanel jPanel = (JPanel) event.getSource();
            Component[] components = jPanel.getComponents();
            for (Component component : components) {
                component.setVisible(!component.isVisible());
                component.setBackground(new Color(85,26,139));
            }
            jPanel.revalidate();
            jPanel.repaint();
        }
    };
}