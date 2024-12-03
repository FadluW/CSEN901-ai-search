package de.giuberlin.search.strategies;

import de.giuberlin.grid.types.GridObject;
import de.giuberlin.search.SearchNode;
import de.giuberlin.search.strategies.heuristics.Heuristic;

import java.util.HashSet;

public class GreedySearch implements Strategy {
    private Heuristic h;
    HashSet<GridObject> visitedObjects = new HashSet<>();

    GreedySearch(Heuristic h) {
        this.h = h;
    }

    @Override
    public void reset() {
        visitedObjects.clear();
    }

    @Override
    public void enqueue(SearchNode node) {
        if (!visitedObjects.add(node.getGridObject())) {
            return;
        }

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enqueue'");
    }

    @Override
    public SearchNode dequeue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dequeue'");
    }

    @Override
    public void displayQueue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayQueue'");
    }

    @Override
    public boolean isInformed() {
        return true;
    }
}
