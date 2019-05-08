package simulation;

import airports.Airports;
import planes.Plane;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Simulation {
    private static Simulation ourInstance = new Simulation();

    public static Simulation getInstance() {
        return ourInstance;
    }

    private Simulation() {
    }

    private final SimulationResources resources = SimulationResources.getInstance();
    private final Airports airports = Airports.getInstance();
    private Set<Plane> planes = new HashSet<>();
    private final int time = 7200;

    private void startSimulation(int planeQty) {
        Random random;
        Plane plane;
        Plane.PlaneBuilder planeBuilder = new Plane.PlaneBuilder();

        int planeModelsSize = resources.planeModels.size();
        int airlinesSize = resources.airlines.size();
        int airportsListSize = resources.airportsLists.size();

        for (int i = 0; i < planeQty; i++) {
            random = new Random();
            plane = planeBuilder
                    .buildPlaneModel(resources.planeModels.get(random.nextInt(planeModelsSize)))
                    .buildAirline(resources.airlines.get(random.nextInt(airlinesSize)))
                    .buildDeparture(resources.airportsLists.get(random.nextInt(airportsListSize)))
                    .build();
            planes.add(plane);
        }
    }

    public void simulate(int planeQty) {
        startSimulation(planeQty);


    }

    public int getTime() {
        return time;
    }

    public Set<Plane> getPlanes() {
        return planes;
    }
}
