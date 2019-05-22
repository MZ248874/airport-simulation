package planes.plane_models;

public class PlaneModel {
    private final String make, modelName;
    private final int maxPassengers;
    //Prędkość w m/s
    private final int velocity;
    private final int flightHeight;

    public PlaneModel(String make, String modelName, int maxPassengers, int velocity, int flightHeight) {
        this.make = make;
        this.modelName = modelName;
        this.maxPassengers = maxPassengers;
        this.velocity = velocity;
        this.flightHeight = flightHeight;
    }

    public String getModelName() {
        return modelName;
    }

    public String getMake() {
        return make;
    }

    public int getFlightHeight() {
        return flightHeight;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public int getVelocity() {
        return velocity;
    }
}
