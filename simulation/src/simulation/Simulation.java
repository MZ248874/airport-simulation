package simulation;

import airports.Airport;
import flight.Flight;
import planes.Plane;
import planes.plane_factory.AbstractPlaneFactory;
import planes.plane_factory.PlaneFactory;

import java.util.Vector;

//Klasa odpowiedzialna za działanie symulacji. Przechowuje listę samolotów
public class Simulation {
    private static Simulation ourInstance = new Simulation();

    public static Simulation getInstance() {
        return ourInstance;
    }

    private Simulation() {
    }

    private static Vector<Plane> planes = new Vector<>();

    //    Rozpoczyna symulację
    public static void startSimulation(int planeQty) {
        //Generowanie pożądanej liczby samolotów
        AbstractPlaneFactory planeFactory = new PlaneFactory();
        for (int i = 0; i < planeQty; i++) {
            addPlane(planeFactory.getPlane());
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

    //    Uruchamia kolejne iteracje symulacji
    public static void simulate() {
        new SimulationThread(0, planes.size()).run();
    }

    public static Vector<Plane> getPlanes() {
        return planes;
    }

    private static void addPlane(Plane plane) {
        planes.add(plane);
    }
}