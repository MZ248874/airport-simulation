package airports;

import data_output.CSV;
import flight.Flight;
import location.Location;

import java.util.Comparator;
import java.util.Vector;

public final class Airport implements CSV {
    private String name;
    private Location location;
    private int importance;
    private long passengersServed, planesServed;
    private Vector<Flight> flights;

    public Airport(String name, Location location, int importance) {
        this.name = name;
        this.location = location;
        this.importance = importance;
        passengersServed = 0;
        planesServed = 0;

        //Automatycznie sortująca się lista
        flights = new Vector<>() {
            @Override
            public boolean add(Flight flight) {
                super.add(flight);
                flights.sort(new sortByImportance());
                return true;
            }
        };
    }

    //Sortowanie dostępnych lotów według współczynnika "importance" lotniska docelowego
    private class sortByImportance implements Comparator<Flight> {

        public int compare(Flight flight1, Flight flight2) {
            return flight1.getDestination().getImportance() - flight2.getDestination().getImportance();
        }
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

    public void removeFlight(int index) {
        try {
            flights.remove(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
    }

    public Vector<Flight> getFlights() {
        return flights;
    }
}
