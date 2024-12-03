package de.giuberlin.search.strategies;

import java.util.HashSet;
import java.util.PriorityQueue;

import de.giuberlin.grid.types.GridObject;
import de.giuberlin.search.SearchNode;
import de.giuberlin.search.strategies.comparators.SearchNodeUniformCostComparator;

public class UniformCostSearch implements Strategy {
    PriorityQueue<SearchNode> queue = new PriorityQueue<>(new SearchNodeUniformCostComparator());
    HashSet<GridObject> visitedObjects = new HashSet<>();

    @Override
    public void reset() {
        visitedObjects.clear();
    }

    @Override
    public void enqueue(SearchNode node) {
        if (!visitedObjects.add(node.getGridObject())) {
            return;
        }

        queue.add(node);
    }

    @Override
    public SearchNode dequeue() {
        return queue.poll();
    }

    @Override
    public void displayQueue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayQueue'");
    }

    @Override
    public boolean isInformed() {
        return false;
    }
}
