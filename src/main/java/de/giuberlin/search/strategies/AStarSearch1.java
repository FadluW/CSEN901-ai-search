package de.giuberlin.search.strategies;

import java.util.PriorityQueue;

import de.giuberlin.search.SearchNode;
import de.giuberlin.search.strategies.comparators.SearchNodeAStarComparator1;

public class AStarSearch1 implements Strategy {
    PriorityQueue<SearchNode> queue = new PriorityQueue<>(new SearchNodeAStarComparator1());

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
        return true;
    }

    @Override
    public void reset() {
        queue.clear();
    }
}
