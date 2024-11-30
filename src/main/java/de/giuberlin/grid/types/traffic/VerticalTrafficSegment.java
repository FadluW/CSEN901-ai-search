package de.giuberlin.grid.types.traffic;

import de.giuberlin.grid.types.GridObject;

public class VerticalTrafficSegment extends TrafficSegment {
    private GridObject northObject;
    private GridObject southObject;

    public VerticalTrafficSegment() {}

    protected void setNorthObject(GridObject northObject) {
        this.northObject = northObject;
    }

    protected void setSouthObject(GridObject southObject) {
        this.southObject = southObject;
    }

    protected GridObject getNorthObject() {
        return northObject;
    }

    protected GridObject getSouthObject() {
        return southObject;
    }
}
