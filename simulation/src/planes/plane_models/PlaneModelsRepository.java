package planes.plane_models;

public class PlaneModelsRepository {
    private static PlaneModelsRepository ourInstance = new PlaneModelsRepository();

    public static PlaneModelsRepository getInstance() {
        return ourInstance;
    }

    private PlaneModelsRepository() {
    }

    private final PlaneModel Q400 = new PlaneModel("Bombardier", "Q400", 80, 185, 8230);
    private final PlaneModel B737 = new PlaneModel("Boeing", "737-800", 215, 222, 12500);
    private final PlaneModel A320 = new PlaneModel("Airbus", "A320", 180, 230, 12000);
    private final PlaneModel A380 = new PlaneModel("Airbus", "A380-800", 853, 283, 10900);

    public PlaneModel getModel(PlaneModels planeModel) {
        switch (planeModel) {
            case Q400:
                return Q400;
            case B737:
                return B737;
            case A320:
                return A320;
            case A380:
                return A380;
            default:
                throw new UnsupportedOperationException("No such model");
        }
    }
}
