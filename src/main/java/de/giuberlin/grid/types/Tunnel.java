package de.giuberlin.grid.types;

public class Tunnel extends GridObject {

    Tunnel otherTunnel;

    public Tunnel(int x, int y) {
        super(x, y);
    }

    public void assignOtherEnd(Tunnel tunnel) {
        otherTunnel = tunnel;
    }
}
