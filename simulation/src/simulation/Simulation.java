package simulation;

import airports.Airport;
import flight.Flight;
import planes.Plane;
import planes.plane_factory.AbstractFactory;
import planes.plane_factory.PlaneFactory;

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

    public static void startSimulation(int planeQty) {
        //Generowanie pożądanej liczby samolotów
        AbstractFactory planeFactory = new PlaneFactory();
        for (int i = 0; i < planeQty; i++) {
            addPlane(planeFactory.getPlane());
        }

        System.out.println(planes.size());

        //Liczba lotów zależy od liczby samolotów.
        //W przypadku niespełenienia warunków lotu przez żaden ze stacjonujących samolotów pozostają 3 zapasowe loty.
        int flightsAmount = (planeQty / SimulationResources.AIRPORTS_LIST.size()) + 3;
        for (Airport airport : SimulationResources.airportsList) {
            for (int i = 0; i < flightsAmount; i++) {
                airport.addFlight(new Flight(airport));
            }
        }
    }

    public static void simulate() {
        new SimulationThread(0, planes.size()).run();
    }

    public int getTime() {
        return TIME;
    }

    public static Vector<Plane> getPlanes() {
        return planes;
    }

    public static void addPlane(Plane plane) {
        planes.add(plane);
    }
}