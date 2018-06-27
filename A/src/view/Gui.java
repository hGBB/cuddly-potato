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
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class Gui extends JFrame implements Observer {
    private int size;
    public JPanel contentPane;
    private JComboBox shapeComboBox;
    private JButton startButton;
    private JButton stopButton;
    private JComboBox sizeComboBox;
    private JComboBox threadComboBox;
    public Grid gridJPanel;
    private Controller controller = new Controller();
    private model.Grid gameOfLife;




    @Override
    public void update(Observable o, Object arg) {

    }

    public Gui() {
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

    private void addMenu() {
        JPanel menu = new JPanel();
        contentPane.add(menu, BorderLayout.SOUTH);
        // add a label.
        JLabel dropdownShapes = new JLabel("Shape:");
        menu.add(dropdownShapes);
        // add the combobox.
        String[] shapes = {"Clear", "Block", "Boat", "Blinker", "Toad", "Glider", "Spaceship", "Pulsar", "Bipole", "Tripole", "r-Pentomino"};
        shapeComboBox = new JComboBox(shapes);
        shapeComboBox.setSelectedIndex(0);
        shapeComboBox.addActionListener(setShape);
        menu.add(shapeComboBox);
        // add start button
        JButton start = new JButton("Start");
        menu.add(start);
        // add stop button
        JButton stop = new JButton("Stop");
        menu.add(stop);
        // add size label
        JLabel dropownSize = new JLabel("Size:");
        menu.add(dropownSize);
        // add size comboBox.
        String[] size = {"Small", "Medium", "Large"};
        sizeComboBox = new JComboBox(size);
        sizeComboBox.setSelectedIndex(1);
        menu.add(sizeComboBox);
        // add thread label
        JLabel dropdownThread = new JLabel("Thread:");
        menu.add(dropdownThread);
        // add thread comboBox.
        String[] thread = {"slow", "normal", "fast"};
        threadComboBox = new JComboBox(thread);
        threadComboBox.setSelectedIndex(1);
        threadComboBox.addActionListener(setThreadSpeed);
        menu.add(threadComboBox);
    }

    private void addGrid(int size) {
        gameOfLife = new GridImpl();
        gameOfLife.resize(10, 10);
        JPanel gridPanel = new JPanel();
        Grid grid = new Grid(gameOfLife);
        contentPane.add(gridPanel, BorderLayout.NORTH);
        gridPanel.add(grid);
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
