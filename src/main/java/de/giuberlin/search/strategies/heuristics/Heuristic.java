package de.giuberlin.search.strategies.heuristics;

import de.giuberlin.grid.types.GridObject;
import de.giuberlin.search.SearchNode;

public class Heuristic {
    private final double HeuristicEuclidean;
    private final int HeuristicManhattan;

    public Heuristic(SearchNode node, GridObject goal) {
        HeuristicEuclidean = goal.euclideanDistanceTo(node.getGridObject());
        HeuristicManhattan = goal.manhattanDistanceTo(node.getGridObject());
    }

    public double getHeuristicEuclidean(){
        return HeuristicEuclidean;
    }

    public int getHeuristicManhattan(){
        return HeuristicManhattan;
    }
}
