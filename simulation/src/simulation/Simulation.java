package simulation;

import airports.Airport;
import flight.Flight;
import planes.Plane;

import java.util.Random;
import java.util.Vector;

public class Simulation {
    private static Simulation ourInstance = new Simulation();

    public static Simulation getInstance() {
        return ourInstance;
    }

    private Simulation() {
    }

    private static Vector<Plane> planes = new Vector<>();
    private final int TIME = 7200;

    public void startSimulation(int planeQty) {
        Random random;
        Plane plane;
        Plane.PlaneBuilder planeBuilder = new Plane.PlaneBuilder();

        int planeModelsSize = SimulationResources.planeModels.size();
        int airlinesSize = SimulationResources.airlines.size();
        int airportsListSize = SimulationResources.AIRPORTS_LIST.size();

        //Generowanie zadanej liczby samolotów i przypisywanie ich do losowych lotnisk
        for (int i = 0; i < planeQty; i++) {
            random = new Random();
            plane = planeBuilder
                    .buildPlaneModel(SimulationResources.planeModels.get(random.nextInt(planeModelsSize)))
                    .buildAirline(SimulationResources.airlines.get(random.nextInt(airlinesSize)))
                    .buildDeparture(SimulationResources.AIRPORTS_LIST.get(random.nextInt(airportsListSize)))
                    .build();
            planes.add(plane);
        }

        //Liczba lotów zależy od liczby samolotów.
        //W przypadku niespełenienia warunków lotu przez żaden ze stacjonujących samolotów pozostają 3 zapasowe loty.
        int flightsAmount = (planeQty / SimulationResources.AIRPORTS_LIST.size()) + 3;
        for (Airport airport : SimulationResources.airportsList) {
            for (int i = 0; i < flightsAmount; i++) {
                airport.addFlight(new Flight(airport));
            }
        }
    }

    public void simulate() {
        //Ilość samolotów jako zmienna pomocnicza w celu obliczenia ilości potrzebnych wątków
        int totalPlanes = planes.size();

        //Jeśli liczba samolotów wynosi przynajmniej 4 tworzone są wątki
        if (totalPlanes > 3) {
            int difference = 0;
            while (totalPlanes % 4 != 0) {
                totalPlanes--;
                difference++;
            }
            int planesPerThread = totalPlanes / 4;

            int i = 0;
            for (; i < planes.size() - 1; i += planesPerThread) {
                SimulationThread simulationThread = new SimulationThread(i, i + planesPerThread);
                simulationThread.start();
            }
            SimulationThread simulationThread = new SimulationThread(i, i + difference);
            simulationThread.start();
        } else new SimulationThread(0, totalPlanes - 1).start();

//        TODO: naprawić tworzenie wątków (NullPointerExceptions)
        SimulationStatistics.addDoneSimulation();
    }

    public int getTime() {
        return TIME;
    }

    public Vector<Plane> getPlanes() {
        return planes;
    }
}