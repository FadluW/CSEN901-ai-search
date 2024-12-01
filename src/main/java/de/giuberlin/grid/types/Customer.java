package de.giuberlin.grid.types;

import de.giuberlin.utilities.TerminalColors;

public class Customer extends GridObject {
    public Customer(int x, int y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return ((isVisited) ? TerminalColors.YELLOW : "") + "C" + TerminalColors.RESET;
    }
}
