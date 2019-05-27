package UI;

import data_output.DataOutput;
import simulation.Simulation;
import simulation.SimulationStatistics;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu extends JFrame {
    private JPanel mainWindow;
    private JProgressBar progressBar1;
    private JButton finishButton;
    private JButton simulateButton;
    private JLabel totalSims;
    private JLabel planes;
    private JLabel timeElapsed;
    private JLabel title;
    private JButton extractData;
    private JFileChooser saveFile;

    private Simulation simulation = Simulation.getInstance();
    private SimulationStatistics simulationStatistics = SimulationStatistics.getInstance();
    private UIHandler uiHandler = UIHandler.getInstance();


    Menu() {
        setup();
        addActionListeners();
        add(mainWindow);
        pack();
    }

    private void setup() {
        setVisible(true);
        setSize(200, 600);
        refreshText();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void refreshText() {
        planes.setText("Planes: " + uiHandler.getData().getPlanesNumber());
        totalSims.setText("Total simulations: " + simulationStatistics.getTotalSimulations());
        timeElapsed.setText("Time elapsed: " + simulationStatistics.getTimeElapsed() / 3600 + " hours");
    }

    private void addActionListeners() {
        simulateButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                simulation.simulate();
                progressBar1.setValue(100);
                progressBar1.setValue(0);
                refreshText();
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

        finishButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(mainWindow,
                        "Do you really want to close the program?",
                        "Warning",
                        dialogButton);
                if (dialogResult == JOptionPane.YES_OPTION) System.exit(0);
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

        extractData.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                saveFile = new JFileChooser();
                FileNameExtensionFilter csvFilter =
                        new FileNameExtensionFilter("Spreadsheet", "csv", "ods", "xls", "xlsx");
                saveFile.setFileFilter(csvFilter);

                if (saveFile.showSaveDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
                    new DataOutput(saveFile.getSelectedFile().getPath() + ".csv");
                    if (Desktop.isDesktopSupported()) {
                        try {
                            Desktop.getDesktop().edit(saveFile.getSelectedFile());
                        } catch (Exception ex) {
                            return;
                        }
                    }
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
