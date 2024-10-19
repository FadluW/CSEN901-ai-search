package de.giuberlin.search.strategies;

public interface Strategy {
    public void enqueue(); // TODO Refactor to add Node parameter
    public void dequeue(); // TODO Refactor to add Node return value
    public void displayQueue();
}
