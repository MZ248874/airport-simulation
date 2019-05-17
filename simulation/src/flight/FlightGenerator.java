package flight;

import airports.Airports;
import airports.AirportsList;
import simulation.SimulationResources;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

class FlightGenerator {
    private static FlightGenerator ourInstance = new FlightGenerator();

    public static FlightGenerator getInstance() {
        return ourInstance;
    }

    private FlightGenerator() {
        airportsLists.sort(new SortByImportance());
    }

    private final Airports airports = Airports.getInstance();
    private List<AirportsList> airportsLists = SimulationResources.getInstance().airportsLists;

    private class SortByImportance implements Comparator<AirportsList> {
        //Klasa implementuje interfejs Comparator i sortuje listę według wielkości współczynnika importance
        public int compare(AirportsList airport1, AirportsList airport2) {
            return airports.getAirport(airport1).getImportance() - airports.getAirport(airport2).getImportance();
        }
    }

    Flight generateFlight(AirportsList airport) {
        //Zmienna lotniska docelowego
        AirportsList compliantAirport = AirportsList.BERLIN;
        //Zmienna ilości pasażerów
        int randomPassengers;

        //Losuje wartośc z przedziału od 0 do 99 (współczynnik procentowy)
        int randomImportance = new Random().nextInt(100);

        //Tworzy kopię listy lotnisk dla pojedynczego użycia
        List<AirportsList> airportsListCopy = airportsLists;

        //Usuwa lotnisko, na którym aktualnie znajduje się samolot
        airportsListCopy.remove(airport);

        //Losuje od końca, aby współczynniki "importance" miały znaczenie procentowych szans
        for (int i = airportsListCopy.size() - 1; i > 0; i--) {
            //Jeśli lotnisko ma zbyt mały współczynnik "importance",
            // to sprawdzane jest kolejne o wyższym bądź równym współczynniku
            if (airports.getAirport(airportsListCopy.get(i)).getImportance() > randomImportance) {
                compliantAirport = airportsListCopy.get(i);
                break;
            }
        }

        //Największy samolot pomieści maksymalnie 853 osoby
        randomPassengers = new Random().nextInt(853);

        return new Flight(compliantAirport, randomPassengers);
    }

}
