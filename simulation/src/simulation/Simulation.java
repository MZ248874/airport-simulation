package simulation;

public class Simulation {
    private static Simulation ourInstance = new Simulation();

    public static Simulation getInstance() {
        return ourInstance;
    }

    private Simulation() {
    }

    private long totalPassengers = 0;
    private long totalFlights = 0;
    private long totalCrashes = 0;

    public void addFlight() {
        totalFlights++;
    }

    public void addTotalPassengers(int passengers) {
        this.totalPassengers += passengers;
    }

    public void addCrash() {
        this.totalCrashes++;
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
}
