package de.giuberlin.search.strategies;

import de.giuberlin.grid.types.GridObject;
import de.giuberlin.search.SearchNode;

import java.util.HashSet;
import java.util.LinkedList;

public class DepthFirstSearch implements Strategy {
    LinkedList<SearchNode> stack = new LinkedList<>();
    HashSet<GridObject> visitedObjects = new HashSet<>();

    @Override
    public void reset() {
        stack.clear();
        visitedObjects.clear();
    }

    @Override
    public void enqueue(SearchNode node) {
        if (!visitedObjects.add(node.getGridObject())) {
            return;
        }

        stack.push(node);
    }

    @Override
    public SearchNode dequeue() {
        return stack.poll();
    }

    @Override
    public void displayQueue() {
        StringBuilder stringBuilder = new StringBuilder("Queue: ");
        stack.forEach(searchNode -> stringBuilder
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
