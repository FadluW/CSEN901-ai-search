package de.giuberlin.search.strategies;

import de.giuberlin.grid.types.GridObject;

public interface InformedSearchStrategy extends Strategy {
    void initializeWithGoal(GridObject goal);
}
