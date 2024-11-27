package de.giuberlin.search.strategies;
import java.util.LinkedList;

import de.giuberlin.search.SearchNode;

public class BreadthFirstSearch implements Strategy {
    LinkedList<SearchNode> queue = new LinkedList<>();

    @Override
    public void enqueue(SearchNode node) {
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
