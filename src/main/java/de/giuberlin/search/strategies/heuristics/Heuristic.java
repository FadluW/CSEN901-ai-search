package de.giuberlin.search.strategies.heuristics;

import de.giuberlin.grid.types.GridObject;
import de.giuberlin.grid.types.traffic.GridNeighbours;
import de.giuberlin.search.SearchNode;

public class Heuristic {
    private final int HeuristicVal1;
    private final int HeuristicVal2;

    public Heuristic(SearchNode node, GridObject goal) {
        HeuristicVal1 = (int) node.getPath().getCost() + goal.euclideanDistanceTo(node.getGridObject());
        HeuristicVal2 = (int) node.getPath().getCost() + goal.manhattanDistanceTo(node.getGridObject());
    }

    public int getHeuristicVal1(){
        return HeuristicVal1;
    }

    public int getHeuristicVal2(){
        return HeuristicVal2;
    }
}
