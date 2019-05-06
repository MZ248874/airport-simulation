package planes;

import airports.Airport;
import location.Location;
import planes.plane_models.PlaneModel;
import planes.plane_models.PlaneModels;
import planes.plane_models.PlaneModelsRepository;
import planes.plane_states.PlaneOnGroundState;
import planes.plane_states.PlaneState;

import java.util.UUID;

public final class Plane {
    private final String ID, airline;
    private int currentPassengers;
    private final PlaneModel planeModel;

    private Location location;
    private PlaneState planeState;
    private Airport departure;
    private Airport arrival;
    private boolean isOperational;

    public Plane(PlaneBuilder planeBuilder) {
        this.airline = planeBuilder.airline;
        this.ID = (planeBuilder.airline.substring(0, 3) + generateID()).toUpperCase();
        this.location = planeBuilder.location;
        this.planeModel = planeBuilder.planeModel;
        this.departure = planeBuilder.departure;
        this.currentPassengers = 0;
        this.planeState = PlaneOnGroundState.getInstance();
        this.isOperational = true;
    }

    public void letsFly(Airport arrival, int passengers) {
        planeState.inFlight(this);
        setArrival(arrival);
        setCurrentPassengers(passengers);
    }

    public void land() {
        planeState.onGround(this);
    }

    public void crash() {
        planeState.crashed(this);
    }

    private static class PlaneBuilder {
        String airline, ID;
        PlaneModel planeModel;
        Location location;
        Airport departure;

        public PlaneBuilder buildAirline(String airline) {
            this.airline = airline;
            return this;
        }

        public PlaneBuilder buildLocation(Location location) {
            this.location = location;
            return this;
        }

        public PlaneBuilder buildPlaneModel(PlaneModels planeModel) {
            this.planeModel = PlaneModelsRepository.getInstance().getModel(planeModel);
            return this;
        }

        public PlaneBuilder buildDeparture(Airport airport) {
            this.departure = airport;
            return this;
        }

        public Plane build() {
            return new Plane(this);
        }
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

    public String getAirline() {
        return airline;
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

    public PlaneState getPlaneState() {
        return planeState;
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
}
