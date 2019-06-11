package planes.plane_factory;

import planes.Plane;
import planes.plane_models.PlaneModels;
import simulation.SimulationResources;

import java.util.Random;

public class B737 implements CreatePlane {
    @Override
    public Plane make() {
        Random random;
        Plane plane;
        Plane.PlaneBuilder planeBuilder = new Plane.PlaneBuilder();

        int airlinesSize = SimulationResources.airlines.size();
        int airportsListSize = SimulationResources.AIRPORTS_LIST.size();

        random = new Random();
        plane = planeBuilder
                .buildPlaneModel(PlaneModels.B737)
                .buildAirline(SimulationResources.airlines.get(random.nextInt(airlinesSize)))
                .buildDeparture(SimulationResources.AIRPORTS_LIST.get(random.nextInt(airportsListSize)))
                .build();
        return plane;
    }
}
