package de.giuberlin.grid.types.traffic;

import de.giuberlin.grid.types.GridObject;

public class HorizontalTrafficSegment extends TrafficSegment {
    private GridObject eastObject;
    private GridObject westObject;

    public HorizontalTrafficSegment() {}

    protected void setEastObject(GridObject eastObject) {
        this.eastObject = eastObject;
    }

    protected void setWestObject(GridObject westObject) {
        this.westObject = westObject;
    }

    protected GridObject getEast() {
        return eastObject;
    }

    protected GridObject getWest() {
        return westObject;
    }
}
