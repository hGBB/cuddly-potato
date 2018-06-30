package view;

import controller.Controller;
import model.GridImpl;
import view.components.Grid;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


@SuppressWarnings("deprecation")
public class Gui extends JFrame implements Observer {
    public JPanel contentPane;
    private JPanel gridJPanel;
    private List<GridCell> cells;
    private Controller controller = new Controller();
    private JLabel counter;
    private model.Grid gameOfLife;
    private boolean go = false;
    private int threadSpeed = 1000;

    @Override
    public void update(Observable o, Object arg) {

    }

    private void updateGrid() {
        for (int i = 0; i < cells.size(); i++) {
            cells.get(i);
        }
    }

    private void updateCounter() {
        counter.setText(String.valueOf(gameOfLife.getGenerations()));
    }


    public Gui(Grid grid) {
        this.gameOfLife = grid;
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0));
        contentPane.add(panel);
        this.addMenu();
        this.addGrid(20);
        controller.addObserver(this);
    }

    @SuppressWarnings("unchecked")
    private void addMenu() {
        JPanel menu = new JPanel();
        contentPane.add(menu, BorderLayout.SOUTH);
        // add the combobox.
        String[] shapes = {"Clear", "Block", "Boat", "Blinker", "Toad", "Glider", "Spaceship", "Pulsar", "Bipole", "Tripole", "r-Pentomino"};
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

    private void addGrid(int size) {
        int height = gameOfLife.getRows();
        int width = gameOfLife.getColumns();
        gridJPanel = new JPanel();
        cells = new ArrayList<>();
        gridJPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                GridCell addCell = new GridCell(gameOfLife.isAlive(j, i), i, j);
                constraints.gridy = i;
                constraints.gridx = j;
            //    constraints.gridheight = 1;
            //    constraints.ipady = 20;
            //    constraints.ipadx = 20;
                Border border = new MatteBorder(1, 1, (i == height - 1 ? 1 : 0), (j == width - 1 ? 1 : 0), Color.BLACK);
                addCell.setBorder(border);
                gridJPanel.add(addCell, constraints);
                cells.add(addCell);
            }
        }
        contentPane.add(gridJPanel, BorderLayout.CENTER);
    }

    public class StartButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        go = true;
        new Thread(() -> {
            while (go && !gameOfLife.getPopulation().isEmpty()) {
                SwingUtilities.invokeLater(() -> controller.startButton());
                try {
                    Thread.sleep(threadSpeed);
                } catch (Exception ignored) {
                }
            }
        }).start();
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


    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Game of Life");

        frame.setContentPane(new Gui().contentPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(100, 100, 600, 400);
        frame.setMinimumSize(new Dimension(450, 200));
        frame.setVisible(true);
        //      Gui gui = new Gui();
        //      gui.setVisible(true);

    }


}
