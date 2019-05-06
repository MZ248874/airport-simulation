package planes.plane_states;

import planes.Plane;

public class PlaneCrashedState implements PlaneState {
    private static PlaneCrashedState ourInstance = new PlaneCrashedState();

    public static PlaneCrashedState getInstance() {
        return ourInstance;
    }

    private PlaneCrashedState() {
    }

    @Override
    public void onGround(Plane plane) {
        //TODO nie wiem co tu dać :(
    }

    @Override
    public void inFlight(Plane plane) {
    }

    @Override
    public void crashed(Plane plane) {
    }
}
