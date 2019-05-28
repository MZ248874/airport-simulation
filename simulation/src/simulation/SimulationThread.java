package simulation;

import airports.Airport;
import flight.Flight;
import planes.Plane;

import java.util.Random;
import java.util.Vector;

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
    }

    private void handlePlane(Plane plane) {
        if (plane.isOperational()) {
            int timeLeft = 0;
            while (timeLeft < TIME) {
                chooseFlight(plane);

                //Samolot ma 1% szans na wypadek w trakcie lotu
                int crash = 50;
                if (new Random().nextInt(100) == crash) {
                    plane.crash();
                    break;
                }

                //TODO: wszystko poniżej
                //Obliczanie odległości między lotniskami
                double distance = plane.getDeparture().getLocation()
                        .distance(plane.getArrival().getLocation());
//                double distance = 1400;
                //Obliczanie czasu lotu. Prędkość samolotu pomnożona przez 75%, aby wziąć pod uwagę czas startu i lądowania
                int flightTime = (int) (distance * 0.75 * plane.getPlaneModel().getVelocity());

                timeLeft += flightTime;
                plane.land();
            }
        }
    }

    private void chooseFlight(Plane plane) {
        Airport departure = plane.getDeparture();
        Vector<Flight> airportFlights = departure.getFlights();
        int maxPlanePassengers = plane.getPlaneModel().getMaxPassengers();
        synchronized (this) {
            for (int i = airportFlights.size() - 1; i > -1; i--) {
                //Wybór odpowiedniego lotu na podstawie dostępnej liczby miejsc dla pasażerów
                Flight flight = airportFlights.get(i);
                airportFlights.remove(flight);
                if (maxPlanePassengers >= flight.getPassengers()) {

                    plane.fly(flight);
                    departure.addFlight(new Flight(departure));

                    break;
                }
                airportFlights.add(flight);
            }
        }
    }
}
