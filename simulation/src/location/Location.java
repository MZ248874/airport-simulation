package location;

public class Location {
    private int x, y, z;

    public Location(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void translate(Location loc2, int time) {
        LinearEquation linearEquation = new LinearEquation(this, loc2);
        double ratio = time / distance(loc2);
        int xDifference = loc2.getX() - getX();
        setX((int) (linearEquation.getA() * xDifference * ratio * getX()));
        setZ(getX() + linearEquation.getB());
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public double distance(Location location) {
        return Math.sqrt((location.getX() - getX()) ^ 2 + (location.getZ() - getZ()) ^ 2);
    }

}
