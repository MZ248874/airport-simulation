package UI;

import data_output.DataOutput;
import simulation.Simulation;
import simulation.SimulationStatistics;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class Menu extends JFrame {
    private JPanel mainWindow;
    private JButton finishButton;
    private JButton simulateButton;
    private JLabel totalSims;
    private JLabel planes;
    private JLabel timeElapsed;
    private JLabel title;
    private JButton extractData;
    private JFileChooser saveFile;

    boolean resultsSavedFlag;

    Menu() {
        setup();
        addActionListeners();
        add(mainWindow);
        pack();
    }

    private void setup() {
        setVisible(true);
        setSize(250, 600);
        refreshText();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void refreshText() {
        planes.setText("Planes: " + UIHandler.getData().getPlanesNumber());
        totalSims.setText("Total simulations: " + SimulationStatistics.getTotalSimulations());
        timeElapsed.setText("Time elapsed: " + SimulationStatistics.getTimeElapsed() / 3600 + " hours");
    }

    private void addActionListeners() {
        simulateButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainWindow.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                simulateButton.setEnabled(false);
                Simulation.simulate();
                mainWindow.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                simulateButton.setEnabled(true);
                refreshText();
                resultsSavedFlag = false;
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
                if (resultsSavedFlag) {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(mainWindow,
                            "Do you really want to close the program?",
                            "Warning",
                            dialogButton);
                    if (dialogResult == JOptionPane.YES_OPTION) System.exit(0);
                } else {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(mainWindow,
                            "You haven't saved your results!\n Would you like to do it now?",
                            "Warning",
                            dialogButton);
                    if (dialogResult == JOptionPane.YES_OPTION) saveDialog();
                    else System.exit(0);
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

        extractData.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                saveDialog();
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

    private void saveDialog() {
        saveFile = new JFileChooser();
        FileNameExtensionFilter csvFilter =
                new FileNameExtensionFilter("Spreadsheet (.csv, .ods, .xls, .xlsx)", "csv", "ods", "xls", "xlsx");
        saveFile.setFileFilter(csvFilter);

        if (saveFile.showSaveDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
            new DataOutput(saveFile.getSelectedFile().getPath() + ".csv");
            resultsSavedFlag = true;

            if (Desktop.isDesktopSupported()) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(mainWindow,
                        "Would You like to open your file?",
                        "Results",
                        dialogButton);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    try {
                        Desktop.getDesktop().open(new File(saveFile.getSelectedFile() + ".csv"));
                    } catch (Exception ex) {
                        new JOptionPane().showMessageDialog(mainWindow, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
}
