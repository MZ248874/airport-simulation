package airports;

import data_output.CSV;
import location.Location;

public final class Airport implements CSV {
    private String name;
    private Location location;
    private int importance;
    private long passengersServed, planesServed;

    public Airport(String name, Location location, int importance) {
        this.name = name;
        this.location = location;
        this.importance = importance;
        passengersServed = 0;
        planesServed = 0;
    }

    public String[] toCSV() {
        String[] result = {name, location.toString(), Long.toString(planesServed), Long.toString(passengersServed)};
        return result;
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
}
