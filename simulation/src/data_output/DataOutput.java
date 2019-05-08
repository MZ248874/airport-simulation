package data_output;

import airports.Airports;
import airports.AirportsList;
import com.opencsv.CSVWriter;
import planes.Plane;
import simulation.Simulation;
import simulation.SimulationResources;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataOutput {
    private final Simulation simulation = Simulation.getInstance();
    private final SimulationResources simulationResources = SimulationResources.getInstance();
    private final Airports airports = Airports.getInstance();
    private String filePath;
    private File CSVFile;

    public DataOutput(String filePath) {
        this.filePath = filePath;
        CSV();
    }

    private void CSV() {
        try {
            CSVFile = new File(filePath);
            FileWriter outputFile = new FileWriter(CSVFile);
            CSVWriter output = new CSVWriter(outputFile);

            String[] spacing = {""};
            String[] header1 = {"Airport simulation results"};
            String[] header2 = {"Airports list"};
            output.writeNext(header1);
            output.writeNext(header2);
            output.writeNext(spacing);

            List<String[]> data = new ArrayList<>();
            for (AirportsList airport : simulationResources.airportsLists) {
                data.add(airports.getAirport(airport).toCSV());
            }
            output.writeAll(data);
            output.writeNext(spacing);
            data.clear();

            String[] header3 = {"Planes list"};
            for (Plane plane : simulation.getPlanes()) {
                data.add(plane.toCSV());
            }
            output.writeAll(data);
            data.clear();

            output.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
