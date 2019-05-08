package flight;

import airports.Airports;
import airports.AirportsList;
import simulation.SimulationResources;

import java.util.Random;

public class Flight {
    private final SimulationResources simulationResources = SimulationResources.getInstance();

    private AirportsList destination;
    private int passengers;

    public Flight() {
        int randomImportance = new Random().nextInt(11);
        boolean flag = true;
        //Importance goes from 1 to 10
        while (this.destination == null) {
            for (AirportsList airport : simulationResources.airportsLists) {
                if (randomImportance < 2) randomImportance = 10;
                int importance = Airports.getInstance().getAirport(airport).getImportance();
                if (randomImportance == importance) {
                    this.destination = airport;
                    break;
                } else randomImportance--;
            }
        }

        int randomPassengers = new Random().nextInt(85);
        this.passengers = randomImportance * randomPassengers;
    }

    public AirportsList getDestination() {
        return destination;
    }

    public int getPassengers() {
        return passengers;
    }
}
