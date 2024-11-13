package de.giuberlin;

import de.giuberlin.exceptions.GoalNotFoundException;
import de.giuberlin.search.SearchNode;
import de.giuberlin.search.strategies.Strategy;
import grid.GridObject;
import grid.types.Tunnel;

public abstract class GenericSearch {

    public String getPathStartingFrom(GridObject startingCell, Strategy searchStrategy) {
        SearchNode nodeToExpand = new SearchNode(startingCell);
        searchStrategy.enqueue(nodeToExpand);
        
        while((nodeToExpand = searchStrategy.dequeue()) != null) {
            if (nodeToExpand.getGridObject().isGoal()) {
                return nodeToExpand.getPath().toString();
            }

            expandNode(nodeToExpand);
        }

        throw new GoalNotFoundException();
    }

    private void expandNode(SearchNode node) {
        // TODO: expand node in all 4 directions + tunnel
        if (node.getGridObject() instanceof Tunnel) {
            // Expand Tunnel
        }
    }
}
