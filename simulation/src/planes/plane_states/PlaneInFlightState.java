package planes.plane_states;

import location.Location;
import planes.Plane;
import simulation.SimulationStatistics;

public class PlaneInFlightState implements PlaneState {
    private static PlaneInFlightState ourInstance = new PlaneInFlightState();

    public static PlaneInFlightState getInstance() {
        return ourInstance;
    }

    private PlaneInFlightState() {
    }

    private final SimulationStatistics simulationStatistics = SimulationStatistics.getInstance();

    @Override
    public void onGround(Plane plane) {
        plane.setDeparture(plane.getArrival());
        simulationStatistics.addFlight();
        simulationStatistics.addTotalPassengers(plane.getCurrentPassengers());
        plane.setPlaneState(PlaneOnGroundState.getInstance());
    }

    @Override
    public void inFlight(Plane plane) {
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
