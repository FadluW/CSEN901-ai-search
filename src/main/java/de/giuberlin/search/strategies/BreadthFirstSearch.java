package de.giuberlin.search.strategies;
import java.util.HashSet;
import java.util.LinkedList;

import de.giuberlin.grid.types.GridObject;
import de.giuberlin.search.SearchNode;

public class BreadthFirstSearch implements Strategy {
    LinkedList<SearchNode> queue = new LinkedList<>();
    HashSet<GridObject> visitedObjects = new HashSet<>();

    @Override
    public void reset() {
        queue.clear();
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
