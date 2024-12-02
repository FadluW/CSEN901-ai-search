package de.giuberlin.grid.types.traffic;

public abstract class TrafficSegment {
    private int traffic;

    public int getTraffic() {
        return traffic;
    }

    public void setTraffic(int traffic) {
        if (traffic < 0) throw new IllegalArgumentException("Traffic must be non-negative.");
        this.traffic = traffic;
    }
}
