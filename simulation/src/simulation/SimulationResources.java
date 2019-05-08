package simulation;

import airports.AirportsList;
import planes.plane_models.PlaneModels;

import java.util.Arrays;
import java.util.List;

public class SimulationResources {

    private static SimulationResources ourInstance = new SimulationResources();

    public static SimulationResources getInstance() {
        return ourInstance;
    }

    private SimulationResources() {
    }

    public final List<PlaneModels> planeModels = Arrays.asList(PlaneModels.values());
    public final List<String> airlines = Arrays.asList("Ryanair", "WizzAir", "LOT", "Lufthansa", "KLM");
    public final List<AirportsList> airportsLists = Arrays.asList(AirportsList.values());
}
