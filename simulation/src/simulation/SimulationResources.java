package simulation;

import airports.Airport;
import airports.Airports;
import airports.AirportsList;
import planes.plane_models.PlaneModels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SimulationResources {

    private static SimulationResources ourInstance = new SimulationResources();

    public static SimulationResources getInstance() {
        return ourInstance;
    }

    private SimulationResources() {
        setupAirportsList();
    }

    public static final List<PlaneModels> planeModels = Arrays.asList(PlaneModels.values());
    public static final List<String> airlines = Arrays.asList("Ryanair", "WizzAir", "LOT", "Lufthansa", "KLM");
    public static final List<AirportsList> AIRPORTS_LIST = Arrays.asList(AirportsList.values());
    public static final List<Airport> airportsList = new ArrayList<>();

    private void setupAirportsList() {
        AIRPORTS_LIST.forEach(airportE -> airportsList.add(Airports.getAirport(airportE)));
        airportsList.sort(new SortByImportance());
    }

    private class SortByImportance implements Comparator<Airport> {
        //Klasa implementuje interfejs Comparator i sortuje listę według wielkości współczynnika importance
        public int compare(Airport airport1, Airport airport2) {
            return airport1.getImportance() - airport2.getImportance();
        }
    }
}
