package planes;

import airports.Airport;
import airports.Airports;
import airports.AirportsList;
import data_output.CSV;
import flight.Flight;
import location.Location;
import planes.plane_models.PlaneModel;
import planes.plane_models.PlaneModels;
import planes.plane_models.PlaneModelsRepository;
import planes.plane_states.PlaneOnGroundState;
import planes.plane_states.PlaneState;

import java.util.UUID;

public final class Plane implements CSV {
    private final String ID, airline;
    private final PlaneModel planeModel;

    private int currentPassengers;
    private Location location;
    private PlaneState planeState;
    private Airport departure;
    private Airport arrival;
    private boolean isOperational;

    private Plane(PlaneBuilder planeBuilder) {
        this.airline = planeBuilder.airline;
        this.ID = (planeBuilder.airline.substring(0, 3) + generateID()).toUpperCase();
        this.planeModel = planeBuilder.planeModel;
        this.departure = planeBuilder.departure;
        this.location = planeBuilder.departure.getLocation();
        this.currentPassengers = 0;
        this.planeState = PlaneOnGroundState.getInstance();
        this.isOperational = true;
    }

    public void fly(Flight flight) {
        setArrival(flight.getDestination());
        setCurrentPassengers(flight.getPassengers());
        planeState.inFlight(this);
    }

    public void land() {
        planeState.onGround(this);
    }

    public void crash() {
        planeState.crashed(this);
    }


    public String[] toCSV() {
        String arrivalCopy;
        if (arrival != null) arrivalCopy = arrival.toString();
        else arrivalCopy = "";
        String departureCopy;
        if (departure != null) departureCopy = departure.toString();
        else departureCopy = "";
        return new String[]{ID, airline,
                planeModel.getMake(),
                planeModel.getModelName(),
                departureCopy,
                arrivalCopy,
                location.toString(),
                Integer.toString(currentPassengers),
                Boolean.toString(isOperational)};
    }

    private String generateID() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5);
    }

    public void setPlaneState(PlaneState planeState) {
        this.planeState = planeState;
    }

    public boolean isOperational() {
        return isOperational;
    }

    public void setOperational(boolean isOperational) {
        this.isOperational = isOperational;
    }

    public String getID() {
        return ID;
    }

    public int getCurrentPassengers() {
        return currentPassengers;
    }

    public PlaneModel getPlaneModel() {
        return planeModel;
    }

    public Location getLocation() {
        return location;
    }

    public void setCurrentPassengers(int currentPassengers) {
        this.currentPassengers = currentPassengers;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setDeparture(Airport departure) {
        this.departure = departure;
    }

    public void setArrival(Airport arrival) {
        this.arrival = arrival;
    }

    public Airport getDeparture() {
        return departure;
    }

    public Airport getArrival() {
        return arrival;
    }

    public static class PlaneBuilder {
        String airline;
        PlaneModel planeModel;
        Airport departure;

        public PlaneBuilder buildAirline(String airline) {
            this.airline = airline;
            return this;
        }

        public PlaneBuilder buildPlaneModel(PlaneModels planeModel) {
            this.planeModel = PlaneModelsRepository.getInstance().getModel(planeModel);
            return this;
        }

        public PlaneBuilder buildDeparture(AirportsList airport) {
            this.departure = Airports.getAirport(airport);
            return this;
        }

        public Plane build() {
            return new Plane(this);
        }
    }
}
