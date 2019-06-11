package location;

//Klasa do wyznaczania równania prostej
class LinearEquation {
    private int A, B;
    private Location loc1, loc2;

    LinearEquation(Location loc1, Location loc2) {
        this.loc1 = loc1;
        this.loc2 = loc2;
        solveEquation();
    }

    private void solveEquation() {
        //A=(z1-z2)/(x1-x2)
        int aToFind;
        int bToFind;
        int topOfFraction = loc1.getZ() - loc2.getZ();
        int bottomOfFraction = loc1.getX() - loc2.getX();
        if (bottomOfFraction == 0) {
            this.A = 1;
            this.B = 0;
            return;
        }
        aToFind = topOfFraction / bottomOfFraction;
        bToFind = loc1.getY() - aToFind * loc1.getX();

        this.A = aToFind;
        this.B = bToFind;
    }

    int getA() {
        return A;
    }

    int getB() {
        return B;
    }
}
