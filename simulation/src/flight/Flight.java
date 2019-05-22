package flight;

import airports.AirportsList;

public class Flight {

    private final AirportsList destination;
    private final int passengers;

    public Flight(AirportsList departure) {
        Flight flight = FlightGenerator.getInstance().generateFlight(departure);
        this.destination = flight.destination;
        this.passengers = flight.passengers;
    }

    Flight(AirportsList destination, int passengers) {
        this.destination = destination;
        this.passengers = passengers;
    }

    public AirportsList getDestination() {
        return destination;
    }

    public int getPassengers() {
        return passengers;
    }
}
