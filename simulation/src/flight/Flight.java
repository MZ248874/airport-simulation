package flight;

import airports.Airport;

public class Flight {

    private final Airport destination;
    private final int passengers;

    public Flight(Airport departure) {
        Flight flight = FlightGenerator.generateFlight(departure);
        this.destination = flight.getDestination();
        this.passengers = flight.getPassengers();
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
