package planes;

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
    private AirportsList departure;
    private AirportsList arrival;
    private boolean isOperational;

    private Plane(PlaneBuilder planeBuilder) {
        this.airline = planeBuilder.airline;
        this.ID = (planeBuilder.airline.substring(0, 3) + generateID()).toUpperCase();
        this.planeModel = planeBuilder.planeModel;
        this.departure = planeBuilder.departure;
        this.location = Airports.getInstance().getAirport(departure).getLocation();
        this.currentPassengers = 0;
        this.planeState = PlaneOnGroundState.getInstance();
        this.isOperational = true;
    }

    public void fly(Flight flight) {
        planeState.inFlight(this);
        setArrival(flight.getDestination());
        setCurrentPassengers(flight.getPassengers());
    }

    public void land() {
        planeState.onGround(this);
    }

    public void crash() {
        planeState.crashed(this);
    }


    public String[] toCSV() {
        String[] result = {ID, airline, planeModel.getMake(), planeModel.getModelName(),
                Airports.getInstance().getAirport(departure).toString(),
                Airports.getInstance().getAirport(arrival).toString(),
                location.toString(),
                Integer.toString(currentPassengers), Boolean.toString(isOperational)};
        return result;
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

    public void setDeparture(AirportsList departure) {
        this.departure = departure;
    }

    public void setArrival(AirportsList arrival) {
        this.arrival = arrival;
    }

    public AirportsList getDeparture() {
        return departure;
    }

    public AirportsList getArrival() {
        return arrival;
    }


    public static class PlaneBuilder {
        String airline, ID;
        PlaneModel planeModel;
        AirportsList departure;

        public PlaneBuilder buildAirline(String airline) {
            this.airline = airline;
            return this;
        }

        public PlaneBuilder buildPlaneModel(PlaneModels planeModel) {
            this.planeModel = PlaneModelsRepository.getInstance().getModel(planeModel);
            return this;
        }

        public PlaneBuilder buildDeparture(AirportsList airport) {
            this.departure = airport;
            return this;
        }

        public Plane build() {
            return new Plane(this);
        }
    }
}
