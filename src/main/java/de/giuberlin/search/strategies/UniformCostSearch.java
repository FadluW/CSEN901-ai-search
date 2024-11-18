package de.giuberlin.search.strategies;

import java.util.PriorityQueue;
import de.giuberlin.search.SearchNode;
import de.giuberlin.search.strategies.comparators.SearchNodeUniformCostComparator;

public class UniformCostSearch implements Strategy {
    PriorityQueue<SearchNode> queue = new PriorityQueue<>(new SearchNodeUniformCostComparator());

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
