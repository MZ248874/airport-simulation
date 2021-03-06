package airports;

import location.Location;

//Repozytorium lotnisk
public class Airports {
    private static Airports ourInstance = new Airports();

    public static Airports getInstance() {
        return ourInstance;
    }

    private Airports() {
    }

    private static final Airport warsaw = new Airport("Warsaw Chopin Airport", new Location(123, 231, 123), 50);
    private static final Airport berlin = new Airport("Berlin International Airport", new Location(321, 5423, 754), 100);
    private static final Airport wroclaw = new Airport("Copernicus Airport Wroclaw", new Location(-165000, 123, -90000), 20);
    private static final Airport lodz = new Airport("Lodz Airport", new Location(0, 185, 0), 15);
    private static final Airport gdansk = new Airport("Gdansk Lech Walesa Airport", new Location(-60000, 148, 285000), 25);
    private static final Airport krakow = new Airport("Krakow John Paul II Airport", new Location(60000, 241, -195000), 30);
    private static final Airport poznan = new Airport("Poznan Airport", new Location(-50000, 150, 0), 10);

    public static Airport getAirport(AirportsList airport) {
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
                throw new UnsupportedOperationException("No such airport");
        }
    }
}
