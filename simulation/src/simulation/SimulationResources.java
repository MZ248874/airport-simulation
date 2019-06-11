package simulation;

import airports.Airport;
import airports.Airports;
import airports.AirportsList;

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

    public static final List<String> airlines = Arrays.asList("Ryanair", "WizzAir", "LOT", "Lufthansa", "KLM");
    public static List<AirportsList> AIRPORTS_LIST;
    public static List<Airport> airportsList;

    private void setupAirportsList() {
        AIRPORTS_LIST = Arrays.asList(AirportsList.values());
        airportsList = new ArrayList<>();
        AIRPORTS_LIST.forEach(airportsList1 -> airportsList.add(Airports.getAirport(airportsList1)));
        airportsList.sort(new SortByImportance());
    }

    private class SortByImportance implements Comparator<Airport> {
        //Klasa implementuje interfejs Comparator i sortuje listę według wielkości współczynnika "importance"
        public int compare(Airport airport1, Airport airport2) {
            return airport1.getImportance() - airport2.getImportance();
        }
    }
}
