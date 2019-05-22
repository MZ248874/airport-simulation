package simulation;

import airports.Airport;
import airports.Airports;
import airports.AirportsList;
import flight.Flight;
import planes.Plane;

import java.util.HashSet;
import java.util.List;
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
    private final SimulationStatistics statistics = SimulationStatistics.getInstance();
    private final Airports airports = Airports.getInstance();
    private Set<Plane> planes = new HashSet<>();
    private final int TIME = 7200;

    private void startSimulation(int planeQty) {
        Random random;
        Plane plane;
        Plane.PlaneBuilder planeBuilder = new Plane.PlaneBuilder();

        int planeModelsSize = resources.planeModels.size();
        int airlinesSize = resources.airlines.size();
        int airportsListSize = resources.airportsLists.size();

        //Generowanie zadanej liczby samolotów i przypisywanie ich do losowych lotnisk
        for (int i = 0; i < planeQty; i++) {
            random = new Random();
            plane = planeBuilder
                    .buildPlaneModel(resources.planeModels.get(random.nextInt(planeModelsSize)))
                    .buildAirline(resources.airlines.get(random.nextInt(airlinesSize)))
                    .buildDeparture(resources.airportsLists.get(random.nextInt(airportsListSize)))
                    .build();
            planes.add(plane);
        }

        //Liczba lotów zależy od liczby samolotów.
        //W przypadku niespełenienia warunków lotu przez żaden ze stacjonujących samolotów pozostają 3 zapasowe loty.
        int flightsAmount = (planeQty / resources.airportsLists.size()) + 3;
        for (AirportsList airport : resources.airportsLists) {
            Airport airport1 = Airports.getInstance().getAirport(airport);
            for (int i = 0; i < flightsAmount; i++) {
                airport1.addFlight(new Flight(airport));
            }
        }
    }

    public void simulate() {
        for (Plane plane : planes) {
            List<Flight> airportFlights = airports.getAirport(plane.getDeparture()).getFlights();
            for (int i = airportFlights.size() - 1; i > -1; i--) {
                if (plane.getPlaneModel().getMaxPassengers() > airportFlights.get(i).getPassengers()) {
                    plane.fly(airportFlights.get(i));
                    airports.getAirport(plane.getDeparture()).removeFlight(i);
                    break;
                }
            }

            //Obliczanie odległości między lotniskami
            double distance = airports.getAirport(plane.getDeparture()).getLocation()
                    .distance(airports.getAirport(plane.getArrival()).getLocation());
            int time = (int) (distance * 0.75 * plane.getPlaneModel().getVelocity());
            //TODO dokończyć: ratio, obliczanie lokalizacji, wątki

        }
        statistics.addDoneSimulation();
    }

    public int getTime() {
        return TIME;
    }

    public Set<Plane> getPlanes() {
        return planes;
    }
}