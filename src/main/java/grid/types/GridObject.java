package grid.types;

import java.awt.Point;

public abstract class GridObject {
    Point coords;
    boolean isVisited;

    public GridObject(int x, int y) {
        coords = new Point(x, y);
        isVisited = false;
    }

    public boolean isGoal() {
        return false;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public int manhattanDistanceTo(GridObject object2) {
        return Math.abs(this.coords.x - object2.coords.x) + Math.abs(this.coords.y - object2.coords.y);
    }
}
