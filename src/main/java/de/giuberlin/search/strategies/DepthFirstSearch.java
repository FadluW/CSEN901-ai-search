package de.giuberlin.search.strategies;

import de.giuberlin.search.SearchNode;

import java.util.LinkedList;

public class DepthFirstSearch implements Strategy {
    LinkedList<SearchNode> stack = new LinkedList<>();

    @Override
    public void enqueue(SearchNode node) {
        stack.push(node);
    }

    @Override
    public SearchNode dequeue() {
        return stack.poll();
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
