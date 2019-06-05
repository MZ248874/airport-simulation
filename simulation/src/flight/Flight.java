package flight;

import airports.Airport;
import airports.Airports;
import airports.AirportsList;

public class Flight {

    private final Airport destination;
    private final int passengers;

    public Flight(Airport departure) {
        Flight flight = FlightGenerator.generateFlight(departure);
        this.destination = flight.getDestination();
        this.passengers = flight.getPassengers();
    }

    public Flight(int passengers) {
        this.destination = Airports.getAirport(AirportsList.BERLIN);
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
