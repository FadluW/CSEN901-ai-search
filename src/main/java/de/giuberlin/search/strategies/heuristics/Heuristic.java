package de.giuberlin.search.strategies.heuristics;

import de.giuberlin.search.SearchNode;

public class Heuristic {
    private final int HeuristicVal1;
    private final int HeuristicVal2;

    public Heuristic(SearchNode node, SearchNode goal) {
        HeuristicVal1 = (int) node.getPath().getCost() + goal.getGridObject().euclideanDistanceTo(node.getGridObject());
        HeuristicVal2 = (int) node.getPath().getCost() + goal.getGridObject().manhattanDistanceTo(node.getGridObject());
    }

    public int getHeuristicVal1(){
        return HeuristicVal1;
    }

    public int getHeuristicVal2(){
        return HeuristicVal2;
    }
}
