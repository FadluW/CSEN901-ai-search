package de.giuberlin.search.strategies.heuristics;

import de.giuberlin.grid.types.GridObject;
import de.giuberlin.search.SearchNode;

public class Heuristic {
    private final SearchNode node;
    private final GridObject goal;

    public Heuristic(SearchNode node, GridObject goal) {
        this.node = node;
        this.goal = goal;
    }

    public double getHeuristicEuclidean() {
        return goal.euclideanDistanceTo(node.getGridObject());
    }

    public int getHeuristicManhattan(){
        return goal.manhattanDistanceTo(node.getGridObject());
    }
}
