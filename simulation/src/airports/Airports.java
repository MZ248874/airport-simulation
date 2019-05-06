package airports;

import location.Location;

public class Airports {
    private static Airports ourInstance = new Airports();

    public static Airports getInstance() {
        return ourInstance;
    }

    private Airports() {
    }

    private final Airport warsaw = new Airport("Warsaw Chopin Airport", new Location(123, 231, 123), 5);
    private final Airport berlin = new Airport("Berlin International Airport", new Location(321, 5423, 754), 10);

    public Airport getAirport(AirportsList airport) {
        switch (airport) {
            case WARSAW:
                return warsaw;
            case BERLIN:
                return berlin;
            default:
                break;
        }
        return null;
    }

}
