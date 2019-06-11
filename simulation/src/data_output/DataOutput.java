package data_output;

import airports.Airports;
import airports.AirportsList;
import com.opencsv.CSVWriter;
import planes.Plane;
import simulation.Simulation;
import simulation.SimulationResources;
import simulation.SimulationStatistics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Generuje plik CSV
public class DataOutput {

    private String filePath;

    public DataOutput(String filePath) {
        this.filePath = filePath;
        CSV();
    }

    private void CSV() {
        try {
            File CSVFile = new File(filePath);
            FileWriter outputFile = new FileWriter(CSVFile);
            CSVWriter output = new CSVWriter(outputFile);

            String[] spacing = {""};
            String[] header1 = {"Airport simulation results"};
            output.writeNext(header1);
            output.writeNext(spacing);


            String[] header2 = {"Simulation statistics"};
            String[] description1 = {"Total simulations",
                    "Total time [s]",
                    "Total flights",
                    "Total passengers",
                    "Total crashes"};
            output.writeNext(header2);
            output.writeNext(description1);
            output.writeNext(SimulationStatistics.getInstance().toCSV());
            output.writeNext(spacing);

            String[] header3 = {"Airports list"};
            String[] description2 = {"Name",
                    "Location",
                    "Planes served",
                    "Passengers served"};
            output.writeNext(header3);
            output.writeNext(description2);
            output.writeNext(spacing);

            List<String[]> data = new ArrayList<>();
            for (AirportsList airport : SimulationResources.AIRPORTS_LIST) {
                data.add(Airports.getAirport(airport).toCSV());
            }
            output.writeAll(data);
            output.writeNext(spacing);
            data.clear();

            String[] header4 = {"Planes list"};
            String[] description3 = {"ID",
                    "Airline",
                    "Make",
                    "Model",
                    "Departure",
                    "Arrival",
                    "Location",
                    "Current passengers",
                    "Is operational"};
            output.writeNext(header4);
            output.writeNext(description3);
            for (Plane plane : Simulation.getPlanes()) {
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
