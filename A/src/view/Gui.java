package view;

import controller.Controller;
import model.Grid;
import view.components.GridCell;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;


@SuppressWarnings("deprecation")
public class Gui extends JFrame implements Observer {
    public JPanel contentPane;
    private JPanel gridJPanel;
    private GridCell[][] cells;
    private Controller controller = new Controller();
    private JLabel counter;
    private model.Grid gameOfLife;
    private boolean go = false;
    private int threadSpeed = 1000;
    private int size;

    @Override
    public void update(Observable o, Object arg) {
        this.gameOfLife = (Grid) arg;
        updateCounter();
        this.addGrid();
        gridJPanel.revalidate();
        gridJPanel.repaint();
    }

    private void updateCounter() {
        counter.setText(String.valueOf(gameOfLife.getGenerations()));
    }


    public Gui(Grid grid) {
        gridJPanel = new JPanel();
        cells = new GridCell[grid.getColumns()][grid.getRows()];
        this.gameOfLife = grid;
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0));
        contentPane.add(panel);
        this.size = 20;
        this.addMenu();
        this.addGrid();
        controller.addObserver(this);
    }

    @SuppressWarnings("unchecked")
    private void addMenu() {
        JPanel menu = new JPanel();
        menu.setBackground(Color.lightGray);
        contentPane.add(menu, BorderLayout.SOUTH);
        // add the combobox.
        String[] shapes = {"Clear", "Block", "Boat", "Blinker",
                "Toad", "Glider", "Spaceship", "Pulsar",
                "Bipole", "Tripole", "r-Pentomino"};
        JComboBox shapeComboBox = new JComboBox(shapes);
        shapeComboBox.setSelectedIndex(0);
        shapeComboBox.addActionListener(new SetShape());
        menu.add(shapeComboBox);
        // add start button
        JButton start = new JButton("Start");
        start.addActionListener(new StartButton());
        menu.add(start);
        // add stop button
        JButton stop = new JButton("Stop");
        stop.addActionListener(new StopButton());
        menu.add(stop);
        // add size label
        JLabel dropownSize = new JLabel("Size:");
        menu.add(dropownSize);
        // add size comboBox.
        String[] size = {"Small", "Medium", "Large"};
        JComboBox sizeComboBox = new JComboBox(size);
        sizeComboBox.setSelectedIndex(1);
        sizeComboBox.addActionListener(new SetPanelSize());
        menu.add(sizeComboBox);
        // add thread label
        JLabel dropdownThread = new JLabel("Thread:");
        menu.add(dropdownThread);
        // add thread comboBox.
        String[] thread = {"slow", "normal", "fast"};
        JComboBox threadComboBox = new JComboBox(thread);
        threadComboBox.setSelectedIndex(1);
        threadComboBox.addActionListener(new SetThreadSpeed());
        menu.add(threadComboBox);
        JLabel generation = new JLabel();
        generation.setText("Gen:");
        menu.add(generation);
        counter = new JLabel();
        counter.setText("0");
        menu.add(counter);
    }

    private void addGrid() {
        gridJPanel = new JPanel();
        gridJPanel.setLayout(new GridBagLayout());
        cells = new GridCell[gameOfLife.getColumns()][gameOfLife.getRows()];
        int height = gameOfLife.getRows();
        int width = gameOfLife.getColumns();
        GridBagConstraints constraints = new GridBagConstraints();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                GridCell addCell = new GridCell(gameOfLife.isAlive(j, i));
                if (gameOfLife.isAlive(j, i)) {
                    addCell.setBackground(Color.decode("#000080"));
                }
                constraints.gridy = i;
                constraints.gridx = j;
                Border border = new MatteBorder(1, 1,
                        (i == height - 1 ? 1 : 0),
                        (j == width - 1 ? 1 : 0), Color.BLACK);
                addCell.setBorder(border);
                addCell.setPreferredSize(new Dimension(size, size));
                gridJPanel.add(addCell, constraints);
                cells[j][i] = addCell;
            }
        }
        contentPane.add(gridJPanel, BorderLayout.CENTER);
    }

    public class StartButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!go) {
                go = true;
                new Thread(() -> {
                    while (go && !gameOfLife.getPopulation().isEmpty()) {
                        SwingUtilities.invokeLater(() ->
                                controller.startButton());
                        try {
                            Thread.sleep(threadSpeed);
                        } catch (Exception ignored) {
                        }
                    }
                }).start();
            }
        }
    }

    public class StopButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            go = false;
        }
    }

    @SuppressWarnings("unchecked")
    public class SetShape implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<String> cb = (JComboBox<String>) e.getSource();
            assert cb.getSelectedItem() != null;
            controller.shapeComboBox(cb.getSelectedItem().toString());
        }
    }

    public class SetPanelSize implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            @SuppressWarnings("unchecked")
            JComboBox<String> cb = (JComboBox<String>) e.getSource();
            assert cb.getSelectedItem() != null;
            Dimension small = new Dimension(10, 10);
            Dimension medium = new Dimension(20, 20);
            Dimension large = new Dimension(30, 30);
            if (cb.getSelectedItem().equals("Small")) {
                size = 10;
                for (GridCell[] gridCells : cells) {
                    for (GridCell gc : gridCells) {
                        gc.setPreferredSize(small);
                    }
                }
            } else if (cb.getSelectedItem().equals("Medium")) {
                size = 20;
                for (GridCell[] gridCells : cells) {
                    for (GridCell gc : gridCells) {
                        gc.setPreferredSize(medium);
                    }
                }
            } else if (cb.getSelectedItem().equals("Large")) {
                size = 30;
                for (GridCell[] gridCells : cells) {
                    for (GridCell gc : gridCells) {
                        gc.setPreferredSize(large);
                    }
                }
            }
            gridJPanel.repaint();
            gridJPanel.revalidate();
        }
    }

    @SuppressWarnings("unchecked")
    public class SetThreadSpeed implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<String> cb = (JComboBox<String>) e.getSource();
            assert cb.getSelectedItem() != null;
            if (cb.getSelectedItem().equals("slow")) {
                threadSpeed = 1500;
            } else if (cb.getSelectedItem().equals("normal")) {
                threadSpeed = 1000;
            } else if (cb.getSelectedItem().equals("fast")) {
                threadSpeed = 500;
            }
        }
    }
}
