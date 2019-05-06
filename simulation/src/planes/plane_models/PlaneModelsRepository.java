package planes.plane_models;

public class PlaneModelsRepository {
    private static PlaneModelsRepository ourInstance = new PlaneModelsRepository();

    public static PlaneModelsRepository getInstance() {
        return ourInstance;
    }

    private PlaneModelsRepository() {
    }

    private final PlaneModel Q400 = new PlaneModel("Bombardier", "Q400", 80, 185, 150);
    private final PlaneModel B737 = new PlaneModel("Boeing", "737-800", 215, 222, 230);
    private final PlaneModel A320 = new PlaneModel("Airbus", "A320", 180, 230, 180);

    public PlaneModel getModel(PlaneModels planeModel) {
        switch (planeModel) {
            case Q400:
                return Q400;
            case B737:
                return B737;
            case A320:
                return A320;
            default:
                throw new UnsupportedOperationException("No such model");
        }
    }

}
