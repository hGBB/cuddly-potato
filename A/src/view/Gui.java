package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class Gui extends JFrame implements Observer {

    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton button1;
    private JButton button2;
    private JComboBox comboBox2;
    private JComboBox comboBox3;

    private Controller controller = new Controller();


    @Override
    public void update(Observable o, Object arg) {

    }

    public Gui() {
        controller.addObserver(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 1000);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Gui");
        frame.setContentPane(new Gui().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //      Gui gui = new Gui();
        //      gui.setVisible(true);

    }


}
