package UI;

import simulation.Simulation;
import simulation.SimulationStatistics;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu extends JFrame {
    private JPanel mainWindow;
    private JProgressBar progressBar1;
    private JButton continueButton;
    private JButton simulateButton;
    private JLabel totalSims;
    private JLabel planes;
    private JLabel timeElapsed;
    private JLabel title;

    private Simulation simulation = Simulation.getInstance();
    private SimulationStatistics simulationStatistics = SimulationStatistics.getInstance();
    private UIHandler uiHandler = UIHandler.getInstance();

    Menu() {
        simulation.startSimulation(uiHandler.getData().getPlanesNumber());
        setup();
        setupText();
        addActionListeners();
        add(mainWindow);
        pack();
    }

    private void setup() {
        setVisible(true);
        setSize(200, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setupText() {
        planes.setText("Planes: " + uiHandler.getData().getPlanesNumber());
        totalSims.setText("Total simulations: " + simulationStatistics.getTotalSimulations());
        timeElapsed.setText("Time elapsed: " + simulationStatistics.getTimeElapsed() / 3600 + " hours");
    }

    private void addActionListeners() {
        simulateButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                progressBar1.setValue(100);
                simulation.simulate();
                progressBar1.setValue(0);
                mainWindow.repaint();
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

        continueButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

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
