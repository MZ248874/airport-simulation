package planes.plane_factory;

import planes.Plane;

import java.util.Random;

public class PlaneFactory extends AbstractFactory {
    @Override
    public Plane getPlane() {
        switch (new Random().nextInt(4)) {
            case 0:
                return new A320().make();
            case 1:
                return new A380().make();
            case 2:
                return new B737().make();
            case 3:
                return new Q400().make();
        }
        return null;
    }
}
