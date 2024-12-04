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
        StringBuilder stringBuilder = new StringBuilder("Queue: ");
        queue.forEach(searchNode -> stringBuilder
                .append("(")
                .append(searchNode.getGridObject().getCoords().x)
                .append(", ")
                .append(searchNode.getGridObject().getCoords().y)
                .append(") "));
        System.out.println(stringBuilder);
    }

    @Override
    public boolean isInformed() {
        return false;
    }
}
