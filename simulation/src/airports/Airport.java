package airports;

import data_output.CSV;
import flight.Flight;
import location.Location;

import java.util.HashSet;
import java.util.Set;

public final class Airport implements CSV {
    private String name;
    private Location location;
    private int importance;
    private long passengersServed, planesServed;
    private Set<Flight> flights;

    public Airport(String name, Location location, int importance) {
        this.name = name;
        this.location = location;
        this.importance = importance;
        passengersServed = 0;
        planesServed = 0;
        flights = new HashSet<>();
    }

    public String[] toCSV() {
        return new String[]{name, location.toString(), Long.toString(planesServed), Long.toString(passengersServed)};
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public int getImportance() {
        return importance;
    }

    public long getPassengersServed() {
        return passengersServed;
    }

    public long getPlanesServed() {
        return planesServed;
    }

    public void addPassengersServed(int passengers) {
        passengersServed += passengers;
    }

    public void addPlanesServed() {
        planesServed++;
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public Set<Flight> getFlights() {
        return flights;
    }
}
