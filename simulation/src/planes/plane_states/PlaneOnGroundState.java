package planes.plane_states;

import location.Location;
import planes.Plane;
import simulation.Simulation;

public class PlaneOnGroundState implements PlaneState {
    private static PlaneOnGroundState ourInstance = new PlaneOnGroundState();

    public static PlaneOnGroundState getInstance() {
        return ourInstance;
    }

    private PlaneOnGroundState() {
    }

    @Override
    public void onGround(Plane plane) {
        throw new UnsupportedOperationException("Plane already on ground");
    }

    @Override
    public void inFlight(Plane plane) {
        plane.setPlaneState(PlaneInFlightState.getInstance());
        plane.getLocation().setY(plane.getPlaneModel().getFlightHeight());
    }

    @Override
    public void crashed(Plane plane) {
        plane.setPlaneState(PlaneCrashedState.getInstance());
        plane.setLocation(new Location(0, 0, 0));
        plane.setCurrentPassengers(0);
        plane.setOperational(false);
        Simulation.getInstance().addCrash();
    }

}
