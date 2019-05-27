package simulation;

import airports.Airports;
import airports.AirportsList;
import flight.Flight;
import planes.Plane;

import java.util.Random;
import java.util.Vector;

public class SimulationThread extends Thread {
    private final Airports airports = Airports.getInstance();
    private Vector<Plane> planes = Simulation.getInstance().getPlanes();
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
//                double distance = airports.getAirport(plane.getDeparture()).getLocation()
//                        .distance(airports.getAirport(plane.getArrival()).getLocation());
                double distance = 1400;
                //Obliczanie czasu lotu. Prędkość samolotu pomnożona przez 75%, aby wziąć pod uwagę czas startu i lądowania
                int flightTime = (int) (distance * 0.75 * plane.getPlaneModel().getVelocity());

                timeLeft += flightTime;
                plane.land();
            }
        }
    }

    private void chooseFlight(Plane plane) {
        AirportsList departure = plane.getDeparture();
        Vector<Flight> airportFlights = airports.getAirport(plane.getDeparture()).getFlights();
        for (int i = airportFlights.size() - 1; i > -1; i--) {
            //Wybór odpowiedniego lotu na podstawie dostępnej liczby miejsc dla pasażerów
            if (plane.getPlaneModel().getMaxPassengers() >= airportFlights.get(i).getPassengers()) {

                plane.fly(airportFlights.get(i));
                airports.getAirport(departure).removeFlight(i);
                airports.getAirport(departure).addFlight(new Flight(departure));

                break;
            }
        }
    }
}
