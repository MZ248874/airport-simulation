package simulation;

import data_output.CSV;
import planes.Plane;

import java.util.HashSet;
import java.util.Set;

public class SimulationStatistics implements CSV {
    private static SimulationStatistics ourInstance = new SimulationStatistics();

    public static SimulationStatistics getInstance() {
        return ourInstance;
    }

    private SimulationStatistics() {
    }

    //Statystyki
    private static long totalPassengers = 0;
    private static long totalFlights = 0;
    private static long totalSimulations = 0;
    private static long timeElapsed;
    private static Set<String> planesCrashed = new HashSet<>();
    private static int totalCrashes = planesCrashed.size();

    @Override
    public String[] toCSV() {
        return new String[]{
                Long.toString(getTotalSimulations()),
                Long.toString(getTimeElapsed()),
                Long.toString(getTotalFlights()),
                Long.toString(getTotalPassengers()),
                Long.toString(getTotalCrashes())};
    }

    public static void addFlight() {
        totalFlights++;
    }

    public static void addTotalPassengers(int passengers) {
        totalPassengers += passengers;
    }

    public static long getTotalPassengers() {
        return totalPassengers;
    }

    public static long getTotalFlights() {
        return totalFlights;
    }

    public static long getTotalCrashes() {
        return totalCrashes;
    }

    public static long getTotalSimulations() {
        return totalSimulations;
    }

    public static void addDoneSimulation() {
        totalSimulations++;
        timeElapsed = totalSimulations * 7200;
    }

    public static void addCrashedPlane(Plane plane) {
        planesCrashed.add(plane.getID());
    }

    public static Set getPlanesCrashedSet() {
        return planesCrashed;
    }

    public static long getTimeElapsed() {
        return timeElapsed;
    }
}
