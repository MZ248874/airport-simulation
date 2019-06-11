package UI;

import javax.swing.*;
import java.awt.*;

//Uruchamia pierwszy ekran GUI
public class UIHandler {

    private static UIHandler ourInstance = new UIHandler();

    public static UIHandler getInstance() {
        return ourInstance;
    }

    private UIHandler() {
    }

    public void start() {
        JFrame intro = new Intro();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        System.out.println(screenSize);
        int x = (screenSize.width - intro.getWidth()) / 2;
        int y = (screenSize.height - intro.getHeight()) / 2;
        intro.setLocation(x, y);
        intro.setVisible(true);
    }

    private static Data data = new Data();

    static Data getData() {
        return data;
    }

    static class Data {

        int planesNumber;

        int getPlanesNumber() {
            return planesNumber;
        }

        void setPlanesNumber(int planesNumber) {
            this.planesNumber = planesNumber;
        }

    }
}
