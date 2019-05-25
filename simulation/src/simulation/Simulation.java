package simulation;

import airports.Airport;
import airports.Airports;
import airports.AirportsList;
import flight.Flight;
import planes.Plane;

import java.util.*;

public class Simulation {
    private static Simulation ourInstance = new Simulation();

    public static Simulation getInstance() {
        return ourInstance;
    }

    private Simulation() {
    }

    private final SimulationResources resources = SimulationResources.getInstance();
    private final SimulationStatistics statistics = SimulationStatistics.getInstance();
    List<Plane> planes = new ArrayList<>();
    private final int TIME = 7200;

    public void startSimulation(int planeQty) {
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
        //Ilość samolotów jako zmienna pomocnicza w celu obliczenia ilości potrzebnych wątków
        int totalPlanes = planes.size();

        //Jeśli liczba samolotów wynosi przynajmniej 4 tworzone są wątki
        if (totalPlanes > 3) {
            int i = 0;
            int planesPerThread = totalPlanes % 4;
            for (; i < planes.size() - 1; i += planesPerThread) {
                SimulationThread simulationThread = new SimulationThread(i, i + planesPerThread);
                simulationThread.start();
            }
            i -= planesPerThread;
            SimulationThread simulationThread = new SimulationThread(i, planes.size() - 1);
            simulationThread.start();
        } else new SimulationThread(0, totalPlanes - 1).start();

//        TODO: naprawić tworzenie wątków (NullPointerExceptions)
        statistics.addDoneSimulation();
    }

    public int getTime() {
        return TIME;
    }

    public List<Plane> getPlanes() {
        return planes;
    }
}