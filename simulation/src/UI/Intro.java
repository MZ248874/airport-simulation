package UI;

import simulation.Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
        addActionListeners();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 600);
    }

    private void addActionListeners() {
        continueButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int planes;
                    planes = Integer.parseInt(planeNumberField.getText());
                    UIHandler.getData().setPlanesNumber(planes);
                    intro.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    continueButton.setEnabled(false);
//                    try {
                    Simulation.startSimulation(UIHandler.getData().getPlanesNumber());
//                    }
//                    catch (Exception exce){
//                        new JOptionPane().showMessageDialog(intro, exce.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                    }
                    setVisible(false);
                    dispose();
                    new Menu();
                } catch (NumberFormatException exc) {
                    new JOptionPane().showMessageDialog(intro, "Use only decimal numbers as input", "Wrong number format", JOptionPane.ERROR_MESSAGE);
                }
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
    }
}
