package de.giuberlin.grid.types;

import de.giuberlin.utilities.TerminalColors;

public class Store extends GridObject {
    public Store(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isGoal() {
        return true;
    }

    @Override
    public String toString() {
        return ((isVisited) ? TerminalColors.YELLOW : "") + "S" + TerminalColors.RESET;
    }
}
