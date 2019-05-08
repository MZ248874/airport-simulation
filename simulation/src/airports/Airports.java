package airports;

import location.Location;

public class Airports {
    private static Airports ourInstance = new Airports();

    public static Airports getInstance() {
        return ourInstance;
    }

    private Airports() {
    }

    private final Airport warsaw = new Airport("Warsaw Chopin Airport", new Location(123, 231, 123), 8);
    private final Airport berlin = new Airport("Berlin International Airport", new Location(321, 5423, 754), 10);
    private final Airport wroclaw = new Airport("Copernicus Airport Wroclaw", new Location(-165000, 123, -90000), 4);
    private final Airport lodz = new Airport("Łódź Airport", new Location(0, 185, 0), 3);
    private final Airport gdansk = new Airport("Gdansk Lech Walesa Airport", new Location(-60000, 148, 285000), 5);
    private final Airport krakow = new Airport("Krakow John Paul II Airport", new Location(60000, 241, -195000), 6);
    private final Airport poznan = new Airport("Poznań Airport", new Location(-50000, 150, 0), 2);

    public Airport getAirport(AirportsList airport) {
        switch (airport) {
            case WARSAW:
                return warsaw;
            case BERLIN:
                return berlin;
            case WROCLAW:
                return wroclaw;
            case LODZ:
                return lodz;
            case GDANSK:
                return gdansk;
            case KRAKOW:
                return krakow;
            case POZNAN:
                return poznan;
            default:
                break;
        }
        return null;
    }
}
