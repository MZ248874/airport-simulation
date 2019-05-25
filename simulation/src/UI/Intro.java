package UI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Intro extends JFrame {
    private JPanel intro;
    private JTextField planeNumberField;
    private JButton continueButton;
    private JLabel description;
    private JLabel title;

    private UIHandler uiHandler = UIHandler.getInstance();

    private void createUIComponents() {
    }

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
                    uiHandler.getData().setPlanesNumber(planes);
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
