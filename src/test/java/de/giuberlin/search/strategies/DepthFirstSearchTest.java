package de.giuberlin.search.strategies;

import de.giuberlin.grid.types.EmptyGridObject;
import de.giuberlin.search.NodePath;
import de.giuberlin.search.SearchNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.giuberlin.search.strategies.SearchStrategyTestUtils.assertQueueEqualsToListNodes;

class DepthFirstSearchTest {
    Strategy depthFirstSearch;

    @BeforeEach
    void setupStrategy() {
        depthFirstSearch = new DepthFirstSearch();
    }

    @Test
    void shouldEnqueueNodes() {
        int orderDiscovered = -1;

        SearchNode node1 = new SearchNode(new EmptyGridObject(0, 1), ++orderDiscovered, new NodePath(), NodePath.Direction.UP);
        SearchNode node2 = new SearchNode(new EmptyGridObject(1, 0), ++orderDiscovered, new NodePath(), NodePath.Direction.UP);
        SearchNode node3 = new SearchNode(new EmptyGridObject(1, 1), ++orderDiscovered, new NodePath(), NodePath.Direction.UP);
        SearchNode node4 = new SearchNode(new EmptyGridObject(0, 2), ++orderDiscovered, new NodePath(), NodePath.Direction.UP);

        depthFirstSearch.enqueue(node1);
        depthFirstSearch.enqueue(node2);
        depthFirstSearch.enqueue(node3);
        depthFirstSearch.enqueue(node4);

        assertQueueEqualsToListNodes(depthFirstSearch, List.of(node4, node3, node2, node1));
    }

    @Test
    void shouldEnqueueNodesWithPathCosts() {
        int orderDiscovered = -1;

        SearchNode node1 = new SearchNode(new EmptyGridObject(0, 1), ++orderDiscovered, new NodePath(), NodePath.Direction.UP, 2);
        SearchNode node2 = new SearchNode(new EmptyGridObject(1, 0), ++orderDiscovered, new NodePath(), NodePath.Direction.UP, 5);
        SearchNode node3 = new SearchNode(new EmptyGridObject(1, 1), ++orderDiscovered, new NodePath(), NodePath.Direction.UP, 3);
        SearchNode node4 = new SearchNode(new EmptyGridObject(0, 2), ++orderDiscovered, new NodePath(), NodePath.Direction.UP, 1);

        depthFirstSearch.enqueue(node1);
        depthFirstSearch.enqueue(node2);
        depthFirstSearch.enqueue(node3);
        depthFirstSearch.enqueue(node4);

        assertQueueEqualsToListNodes(depthFirstSearch, List.of(node4, node3, node2, node1));
    }
}