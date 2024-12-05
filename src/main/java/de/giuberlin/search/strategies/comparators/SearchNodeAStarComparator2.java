package de.giuberlin.search.strategies.comparators;

import java.util.Comparator;

import de.giuberlin.grid.types.GridObject;
import de.giuberlin.search.strategies.heuristics.Heuristic;
import de.giuberlin.search.SearchNode;

public class SearchNodeAStarComparator2 implements Comparator<SearchNode>  {
    GridObject goal;

    public SearchNodeAStarComparator2(GridObject goal) {
        this.goal = goal;
    }

    @Override
    public int compare(SearchNode node1, SearchNode node2) { //comprae between h1 of node 1 and 2
        Heuristic node1H =new Heuristic(node1, goal);
        Heuristic node2H =new Heuristic(node2, goal);

        int costCompare = Long.compare(
                node1.getPath().getCost() + node1H.getHeuristicManhattan(),
                node2.getPath().getCost()+ node2H.getHeuristicManhattan());

        if (costCompare != 0) {
            return costCompare;
        }

        return Integer.compare(node1.getOrderDiscovered(), node2.getOrderDiscovered());
    }
}
