package grid;

import java.awt.Point;

public abstract class GridObject {
    Point coords;


    public boolean isGoal() {
        return false;
    }

    public int manhattanDistanceTo(GridObject object2) {
        return Math.abs(this.coords.x - object2.coords.x) + Math.abs(this.coords.y - object2.coords.y);
    }
}
