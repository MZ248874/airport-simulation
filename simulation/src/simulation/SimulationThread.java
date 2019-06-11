package simulation;

import airports.Airport;
import flight.Flight;
import planes.Plane;

import java.util.Random;
import java.util.Vector;

//Przeprowadza symulację
public class SimulationThread extends Thread {

    private Vector<Plane> planes = Simulation.getPlanes();
    private final int TIME = 7200;

    private int index;
    private int endIndex;

    SimulationThread(int index, int endIndex) {
        this.index = index;
        this.endIndex = endIndex;
    }

    public void run() {
        for (; index < endIndex; index++) {
            handlePlane(planes.get(index));
        }
        SimulationStatistics.addDoneSimulation();
    }

    private void handlePlane(Plane plane) {
        if (plane.isOperational()) {
            int timeLeft = 0;
            Random random = new Random();
            while (timeLeft < TIME) {
                chooseFlight(plane);

                //Samolot ma 1% szans na wypadek w trakcie lotu
                if (random.nextInt(1000) == 50) {
                    plane.crash();
                    break;
                }

                int flightTime = flightTime(plane);

                timeLeft += flightTime;
                plane.land();
            }
        }
    }

    private void chooseFlight(Plane plane) {
        Airport departure = plane.getDeparture();
        Vector<Flight> airportFlights = departure.getFlights();
        int maxPlanePassengers = plane.getPlaneModel().getMaxPassengers();
        for (int i = airportFlights.size() - 1; i > -1; i--) {
            //Wybór odpowiedniego lotu na podstawie dostępnej liczby miejsc dla pasażerów
            Flight flight = airportFlights.get(i);
            if (maxPlanePassengers >= flight.getPassengers()) {
                plane.fly(flight);
                departure.removeFlight(flight);
                departure.addFlight(new Flight(departure));
                break;
            }
        }
        if (plane.getArrival() == null) {
            plane.fly(new Flight(maxPlanePassengers));
        }
    }

    private int flightTime(Plane plane) {
        //Obliczanie czasu lotu. Prędkość samolotu pomnożona przez 75%, aby wziąć pod uwagę czas startu i lądowania
        return (int) Math.abs((distance(plane) * 0.75 * plane.getPlaneModel().getVelocity()));
    }

    private double distance(Plane plane) {
        //Obliczanie odległości między lotniskami
        return plane.getDeparture().getLocation()
                .distance(plane.getArrival().getLocation());
    }
}
