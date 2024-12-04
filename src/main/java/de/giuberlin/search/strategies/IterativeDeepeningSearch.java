package de.giuberlin.search.strategies;

import de.giuberlin.search.SearchNode;

public class IterativeDeepeningSearch extends DepthFirstSearch {
    int currentLevel;

    @Override
    public void enqueue(SearchNode node) {
        if (node.getPath().getDepth() > currentLevel) return;
        super.enqueue(node);
    }

    public void setCurrentLevel(int level) {
        this.currentLevel = level;
    }
}
