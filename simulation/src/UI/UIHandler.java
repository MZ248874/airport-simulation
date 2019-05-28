package UI;

import simulation.Simulation;

public class UIHandler {

    private static UIHandler ourInstance = new UIHandler();

    public static UIHandler getInstance() {
        return ourInstance;
    }

    private UIHandler() {
    }

    public void start() {
        new Intro();
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
