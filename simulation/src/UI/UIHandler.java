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

    private Data data = new Data();

    Data getData() {
        return data;
    }

    class Data {

        int planesNumber;
        String filePath;
        String fileName;
        Simulation simulation = Simulation.getInstance();

        int getPlanesNumber() {
            return planesNumber;
        }

        public String getFilePath() {
            return filePath;
        }

        public String getFileName() {
            return fileName;
        }

        public Simulation getSimulation() {
            return simulation;
        }

        void setPlanesNumber(int planesNumber) {
            this.planesNumber = planesNumber;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }
}