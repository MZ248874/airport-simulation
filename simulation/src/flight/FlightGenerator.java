package flight;

import airports.Airport;
import airports.Airports;
import airports.AirportsList;
import simulation.SimulationResources;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Generuje lot
class FlightGenerator {
    private static FlightGenerator ourInstance = new FlightGenerator();

    public static FlightGenerator getInstance() {
        return ourInstance;
    }

    private FlightGenerator() {
    }

    static Flight generateFlight(Airport airport) {
        //Zmienna lotniska docelowego
        Airport compliantAirport = Airports.getAirport(AirportsList.BERLIN);
        //Zmienna ilości pasażerów
        int randomPassengers;

        //Największy samolot pomieści maksymalnie 853 osoby
        randomPassengers = new Random().nextInt(853);

        //Losuje wartośc z przedziału od 0 do 99 (współczynnik procentowy)
        int randomImportance = new Random().nextInt(100);

        //Tworzy kopię listy lotnisk dla pojedynczego użycia
        List<Airport> airportsListCopy = new ArrayList<>(List.copyOf(SimulationResources.airportsList));
        //Usuwa lotnisko, na którym aktualnie znajduje się samolot
        airportsListCopy.remove(airport);

        //Losuje od końca, aby współczynniki "importance" miały znaczenie procentowych szans
        for (int i = airportsListCopy.size() - 1; i > -1; i--) {
            //Jeśli lotnisko ma zbyt mały współczynnik "importance",
            // to sprawdzane jest kolejne o wyższym bądź równym współczynniku
            if (airportsListCopy.get(i).getImportance() > randomImportance) {
                compliantAirport = airportsListCopy.get(i);
                break;
            }
        }

        return new Flight(compliantAirport, randomPassengers);
    }

}
