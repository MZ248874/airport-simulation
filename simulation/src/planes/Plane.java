package planes;

import location.Location;

import java.util.UUID;

public final class Plane {
    private final String ID, airline;
    private int currentPassengers;
    PlaneModel planeModel;
    Location location;

    public Plane(PlaneBuilder planeBuilder) {
        this.airline = planeBuilder.airline;
        this.ID = (planeBuilder.airline.substring(0, 3) + planeBuilder.ID).toUpperCase();
        this.location = planeBuilder.location;
        this.planeModel = planeBuilder.planeModel;
    }

    private static class PlaneBuilder {
        String airline, ID;
        PlaneModel planeModel;
        Location location;

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

        public PlaneBuilder buildID() {
            String uniqueID = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5);
            this.ID = uniqueID;
            return this;
        }

        public Plane build() {
            return new Plane(this);
        }
    }
}
