package simulation;

import airports.AirportsList;
import planes.Plane;
import planes.plane_models.PlaneModels;

import java.util.*;

public class Simulation {
    private static Simulation ourInstance = new Simulation();

    public static Simulation getInstance() {
        return ourInstance;
    }

    private Simulation() {
    }

    private Set<Plane> planes = new HashSet<>();
    private int time = 7200;

    private void startSimulation(int planeQty) {
        Random random;
        Plane plane;
        Plane.PlaneBuilder planeBuilder = new Plane.PlaneBuilder();
        List<PlaneModels> planeModels = Arrays.asList(PlaneModels.values());
        List<String> airlines = Arrays.asList("Ryanair", "WizzAir", "LOT", "Lufthansa", "KLM");
        List<AirportsList> airportsLists = Arrays.asList(AirportsList.values());
        int planeModelsSize = planeModels.size();
        int airlinesSize = airlines.size();
        int airportsListSize = airportsLists.size();

        for (int i = 0; i < planeQty; i++) {
            random = new Random();
            plane = planeBuilder
                    .buildPlaneModel(planeModels.get(random.nextInt(planeModelsSize)))
                    .buildAirline(airlines.get(random.nextInt(airlinesSize)))
                    .buildDeparture(airportsLists.get(random.nextInt(airportsListSize)))
                    .build();
            planes.add(plane);
        }
    }

    public static void simulate() {

    }

    int getTime() {
        return time;
    }
}
