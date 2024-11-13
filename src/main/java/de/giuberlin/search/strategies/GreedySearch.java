package de.giuberlin.search.strategies;

import de.giuberlin.search.SearchNode;
import de.giuberlin.search.strategies.heuristics.Heuristic;

public class GreedySearch implements Strategy {
    private Heuristic h;
    
    GreedySearch(Heuristic h) {
        this.h = h;
    }
    
    @Override
    public void enqueue(SearchNode node) {
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
