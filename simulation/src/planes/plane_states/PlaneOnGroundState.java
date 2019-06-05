package planes.plane_states;

import location.Location;
import planes.Plane;
import simulation.SimulationStatistics;

public class PlaneOnGroundState implements PlaneState {
    private static PlaneOnGroundState ourInstance = new PlaneOnGroundState();

    public static PlaneOnGroundState getInstance() {
        return ourInstance;
    }

    private PlaneOnGroundState() {
    }

    @Override
    public void onGround(Plane plane) {
    }

    @Override
    public void inFlight(Plane plane) {
        plane.getLocation().setY(plane.getPlaneModel().getFlightHeight());
        plane.setPlaneState(PlaneInFlightState.getInstance());
    }

    @Override
    public void crashed(Plane plane) {
        plane.setLocation(new Location(0, 0, 0));
        plane.setCurrentPassengers(0);
        plane.setOperational(false);
        SimulationStatistics.addCrashedPlane(plane);
        plane.setPlaneState(PlaneCrashedState.getInstance());
    }

}
