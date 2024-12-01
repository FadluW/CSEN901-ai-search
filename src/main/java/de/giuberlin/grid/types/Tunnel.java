package de.giuberlin.grid.types;
import java.awt.Point;

import de.giuberlin.utilities.TerminalColors;

public class Tunnel extends GridObject {
    private Tunnel otherExit;

    public Tunnel(int x, int y) {
        super(x, y);
    }

    private Tunnel(int x, int y, Tunnel otherExit) {
        super(x, y);
        this.otherExit = otherExit;
    }
    
    public Tunnel getOtherExit() {
        return otherExit;
    }

    public Tunnel createAndLinkOtherExitAt(Point coords) {
        Tunnel otherExit = new Tunnel(coords.x, coords.y, this);
        this.otherExit = otherExit;

        return otherExit;
    }

    public int getCost() {
        return this.manhattanDistanceTo(otherExit);
    }

    @Override
    public String toString() {
        return ((isVisited) ? TerminalColors.YELLOW : "") + "T" + TerminalColors.RESET;
    }
}
