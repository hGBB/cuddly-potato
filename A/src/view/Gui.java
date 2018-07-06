package view;

import controller.Controller;
import model.Grid;
import view.components.GridCell;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
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
    private int threadSpeed = 1000;
    private int size;
    private boolean mousePressedDown;
    private boolean setAliveOrDead;
    private Timer checkTime = new Timer(threadSpeed,
            e -> controller.startButton());
    private int initialWidth = 0;
    private int initialHeight = 0;


    @Override
    public void update(Observable o, Object arg) {
        this.gameOfLife = (Grid) arg;
        counter.setText(String.valueOf(gameOfLife.getGenerations()));
        this.addGrid();
        gridJPanel.revalidate();
        gridJPanel.repaint();
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
        this.mousePressedDown = false;
        this.size = 20;
        this.addMenu();
        controller.addObserver(this);
        gridJPanel = new JPanel();
        contentPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                doResize();
                //        addGrid();
            }
        });
        this.addGrid();
        contentPane.add(gridJPanel, BorderLayout.CENTER);

    }

    private void doResize() {
        // round to nearest **50 to avoid overusing the resize method
        int newHeight = ((gridJPanel.getHeight() + 25) / 50) * 50;
        int newWidth = ((gridJPanel.getWidth() + 25) / 50) * 50;
        if ((newHeight > 400 && newHeight != initialHeight)
                || (newWidth != initialWidth && newWidth > 600)) {
            // we have to take the border into account when we calculate
            // the new number of cells.
            int widthCoefficient = gameOfLife.getColumns() + 2;
            int heightCoefficient = gameOfLife.getRows() + 2;
            initialWidth = newWidth;
            initialHeight = newHeight;
            controller.resizeGrid((initialWidth - widthCoefficient)
                            / size,
                    (initialHeight - heightCoefficient) / size);
        }
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
        shapeComboBox.addActionListener(e -> {
            JComboBox<String> cb = (JComboBox<String>) e.getSource();
            assert cb.getSelectedItem() != null;
            controller.shapeComboBox(cb.getSelectedItem().toString());
        });
        menu.add(shapeComboBox);
        // add start button
        JButton start = new JButton("Start");
        start.addActionListener(e -> checkTime.start());
        menu.add(start);
        // add stop button
        JButton stop = new JButton("Stop");
        stop.addActionListener(e -> checkTime.stop());
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
        int width = gameOfLife.getColumns();
        int height = gameOfLife.getRows();
        if (cells[0][0] != null && width == cells.length && height == cells[0].length) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (gameOfLife.isAlive(j, i)) {
                        cells[j][i].setBackground(Color.blue);
                    } else {
                        cells[j][i].setBackground(Color.white);
                    }
                }
            }
        } else {
            fillGrid(width, height);
        }
    }

    private void fillGrid(int width, int height) {
        gridJPanel.removeAll();
        gridJPanel.setLayout(new GridBagLayout());
        cells = new GridCell[gameOfLife.getColumns()][gameOfLife.getRows()];
        GridBagConstraints constraints = new GridBagConstraints();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                GridCell addCell = new GridCell(j, i, gameOfLife.isAlive(j, i));
                if (addCell.isAlive()) {
                    addCell.setBackground(Color.red);
                }
                constraints.gridy = i;
                constraints.gridx = j;
                Border border = new MatteBorder(1, 1,
                        (i == height - 1 ? 1 : 0),
                        (j == width - 1 ? 1 : 0), Color.BLACK);
                addCell.setBorder(border);
                addCell.setPreferredSize(new Dimension(size, size));
                gridJPanel.add(addCell, constraints);
                addCell.addMouseListener(new CellActiveListener());
                cells[j][i] = addCell;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public class CellActiveListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            GridCell cell = (GridCell) e.getSource();
            cell.setAlive(!cell.isAlive());
            controller.changeCellStatus(cell.getCol(),
                    cell.getRow(), !cell.isAlive());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mousePressedDown = false;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (!mousePressedDown) {
                GridCell cell = (GridCell) e.getSource();
                setAliveOrDead = !cell.isAlive();
                controller.changeCellStatus(cell.getCol(),
                        cell.getRow(), setAliveOrDead);
                cell.setAlive(setAliveOrDead);
                mousePressedDown = true;
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            changeCellStatus(e);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            changeCellStatus(e);
        }

        private void changeCellStatus(MouseEvent e) {
            if (mousePressedDown) {
                GridCell cell = (GridCell) e.getSource();
                if (cell.isAlive() != setAliveOrDead) {
                    controller.changeCellStatus(cell.getCol(),
                            cell.getRow(), setAliveOrDead);
                }
                cell.setAlive(setAliveOrDead);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public class SetPanelSize implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            @SuppressWarnings("unchecked")
            JComboBox<String> cb = (JComboBox<String>) e.getSource();
            assert cb.getSelectedItem() != null;
            if (cb.getSelectedItem().equals("Small")) {
                size = 10;
                resizeCells();
            } else if (cb.getSelectedItem().equals("Medium")) {
                size = 20;
                resizeCells();
            } else if (cb.getSelectedItem().equals("Large")) {
                size = 30;
                resizeCells();
            }
            gridJPanel.repaint();
            gridJPanel.revalidate();
        }

        private void resizeCells() {
            for (GridCell[] gridCells : cells) {
                for (GridCell gc : gridCells) {
                    gc.setPreferredSize(new Dimension(size, size));
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public class SetThreadSpeed implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JComboBox<String> cb = (JComboBox<String>) event.getSource();
            assert cb.getSelectedItem() != null;
            checkTime.stop();
            if (cb.getSelectedItem().equals("slow")) {
                threadSpeed = 1500;
            } else if (cb.getSelectedItem().equals("normal")) {
                threadSpeed = 1000;
            } else if (cb.getSelectedItem().equals("fast")) {
                threadSpeed = 500;
            }
            checkTime = new Timer(threadSpeed, e -> controller.startButton());
            checkTime.start();
        }
    }
}
