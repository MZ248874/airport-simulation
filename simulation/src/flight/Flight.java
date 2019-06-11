package flight;

import airports.Airport;
import airports.Airports;
import simulation.SimulationResources;

import java.util.Random;

//Zawiera cel podróży i liczbę pasażerów
public class Flight {

    private final Airport destination;
    private final int passengers;

    public Flight(Airport departure) {
        Flight flight = FlightGenerator.generateFlight(departure);
        this.destination = flight.getDestination();
        this.passengers = flight.getPassengers();
    }

    public Flight(int passengers) {
        Random randomAirport = new Random();
        this.destination = Airports.getAirport(SimulationResources.AIRPORTS_LIST.
                get(randomAirport.nextInt(SimulationResources.AIRPORTS_LIST.size())));
        this.passengers = passengers;
    }

    Flight(Airport destination, int passengers) {
        this.destination = destination;
        this.passengers = passengers;
    }

    public Airport getDestination() {
        return destination;
    }

    public int getPassengers() {
        return passengers;
    }
}
