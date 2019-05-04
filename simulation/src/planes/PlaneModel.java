package planes;

public class PlaneModel {
    private final String make, model;
    private final int maxPassengers;
    private final int velocity;

    public PlaneModel(String make, String model, int maxPassengers, int velocity) {
        this.make = make;
        this.model = model;
        this.maxPassengers = maxPassengers;
        this.velocity = velocity;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public int getVelocity() {
        return velocity;
    }
}
