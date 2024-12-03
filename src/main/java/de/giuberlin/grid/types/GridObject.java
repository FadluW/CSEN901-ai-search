package de.giuberlin.grid.types;

import de.giuberlin.grid.types.traffic.GridNeighbours;
import de.giuberlin.utilities.TerminalColors;

import java.awt.Point;

public abstract class GridObject {
    protected Point coords;
    protected boolean isVisited;
    protected GridNeighbours neighbours;

    public GridObject(int x, int y) {
        coords = new Point(x, y);
        isVisited = false;
        neighbours = new GridNeighbours();
    }

    public Point getCoords() {
        return coords;
    }

    public GridNeighbours getNeighbours() {
        return neighbours;
    }

    public boolean isGoal() {
        return false;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public String toString() {
        return ((isVisited) ? TerminalColors.YELLOW : "") + "+" + TerminalColors.RESET;
    }

    public int manhattanDistanceTo(GridObject object2) {
        return Math.abs(this.coords.x - object2.coords.x) + Math.abs(this.coords.y - object2.coords.y);
    }

    public int euclideanDistanceTo(GridObject object2) {
        // TODO: return rounded euclidean distance
        // ?: Should it be decimal?
        return 0;
    }
}
