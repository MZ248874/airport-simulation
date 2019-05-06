package planes.plane_states;

import planes.Plane;

public interface PlaneState {

    void onGround(Plane plane);

    void inFlight(Plane plane);

    void crashed(Plane plane);

}
