package de.giuberlin.search.strategies;

import de.giuberlin.search.SearchNode;

public interface Strategy {
    public void enqueue(SearchNode node); // TODO Refactor to add Node parameter
    public SearchNode dequeue(); // TODO Refactor to add Node return value
    public void displayQueue();
    public boolean isInformed();
}
