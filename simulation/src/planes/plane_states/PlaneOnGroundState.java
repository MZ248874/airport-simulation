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

    private final SimulationStatistics simulationStatistics = SimulationStatistics.getInstance();

    @Override
    public void onGround(Plane plane) {
    }

    @Override
    public void inFlight(Plane plane) {
        plane.setPlaneState(PlaneInFlightState.getInstance());
        plane.getLocation().setY(plane.getPlaneModel().getFlightHeight());
    }

    @Override
    public void crashed(Plane plane) {
        plane.setLocation(new Location(0, 0, 0));
        plane.setCurrentPassengers(0);
        plane.setOperational(false);
        simulationStatistics.addCrashedPlane(plane.getID());
        plane.setPlaneState(PlaneCrashedState.getInstance());
    }

}
