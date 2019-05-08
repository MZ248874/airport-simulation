package simulation;

import java.util.HashSet;
import java.util.Set;

public class SimulationStatistics {
    private static SimulationStatistics ourInstance = new SimulationStatistics();

    public static SimulationStatistics getInstance() {
        return ourInstance;
    }

    private SimulationStatistics() {
    }

    //statistics
    private long totalPassengers = 0;
    private long totalFlights = 0;
    private long totalSimulations = 0;
    private long timeElapsed = totalSimulations * Simulation.getInstance().getTime();
    private Set<String> planesCrashed = new HashSet<>();
    private int totalCrashes = planesCrashed.size();
    //----------

    public void addFlight() {
        totalFlights++;
    }

    public void addTotalPassengers(int passengers) {
        this.totalPassengers += passengers;
    }

    public long getTotalPassengers() {
        return totalPassengers;
    }

    public long getTotalFlights() {
        return totalFlights;
    }

    public long getTotalCrashes() {
        return totalCrashes;
    }

    public long getTotalSimulations() {
        return totalSimulations;
    }

    public void setTotalSimulations(long totalSimulations) {
        this.totalSimulations = totalSimulations;
    }

    public void addCrashedPlane(String ID) {
        planesCrashed.add(ID);
    }

    public Set getPlanesCrashedSet() {
        return planesCrashed;
    }
}
