package de.giuberlin.search.strategies;

import de.giuberlin.search.SearchNode;

public interface Strategy {
    public void enqueue(SearchNode node);
    public SearchNode dequeue();
    public void displayQueue();
    public boolean isInformed();
}
