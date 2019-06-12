package UI;

import simulation.Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Pierwszy ekran GUI
public class Intro extends JFrame {
    private JPanel intro;
    private JTextField planeNumberField;
    private JButton continueButton;
    private JLabel description;
    private JLabel title;

    Intro() {
        setup();
        add(intro);
        pack();
    }

    private void setup() {
        setSize(400, 600);
        addActionListeners();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void addActionListeners() {
        continueButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nextWindow();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        continueButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) nextWindow();
            }
        });
    }

    private void nextWindow() {
        try {
            int planes;
            planes = Integer.parseInt(planeNumberField.getText());
            UIHandler.getData().setPlanesNumber(planes);
            intro.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            continueButton.setEnabled(false);
            Simulation.startSimulation(UIHandler.getData().getPlanesNumber());
            setVisible(false);
            dispose();

            Menu menu = new Menu();
            menu.setLocationRelativeTo(intro);

        } catch (NumberFormatException exc) {
            new JOptionPane().showMessageDialog(intro, "Use only decimal numbers as input", "Wrong number format", JOptionPane.ERROR_MESSAGE);
        }
    }
}
