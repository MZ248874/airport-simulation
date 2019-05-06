package planes.plane_states;

import location.Location;
import planes.Plane;
import simulation.Simulation;

public class PlaneInFlightState implements PlaneState {
    private static PlaneInFlightState ourInstance = new PlaneInFlightState();

    public static PlaneInFlightState getInstance() {
        return ourInstance;
    }

    private PlaneInFlightState() {
    }

    @Override
    public void onGround(Plane plane) {
        plane.setDeparture(plane.getArrival());
        Simulation.getInstance().addFlight();
        Simulation.getInstance().addTotalPassengers(plane.getCurrentPassengers());
        plane.setPlaneState(PlaneOnGroundState.getInstance());
    }

    @Override
    public void inFlight(Plane plane) {
        //TODO jakieś rozwiązanie; napisać system komunikatów
//        throw new UnsupportedOperationException("Already in flight!");
    }

    @Override
    public void crashed(Plane plane) {
        plane.setPlaneState(PlaneCrashedState.getInstance());
        plane.setLocation(new Location(0, 0, 0));
        plane.setCurrentPassengers(0);
        plane.setOperational(false);
        plane.setPlaneState(PlaneCrashedState.getInstance());
    }
}
