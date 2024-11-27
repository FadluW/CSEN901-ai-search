package de.giuberlin.search.strategies;

import de.giuberlin.search.NodePath;
import de.giuberlin.search.SearchNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.giuberlin.search.strategies.SearchStrategyTestUtils.assertQueueEqualsToListNodes;

class BreadthFirstSearchTest {
    Strategy breadthFirstSearch;

    @BeforeEach
    void setupStrategy() {
        breadthFirstSearch = new BreadthFirstSearch();
    }

    @Test
    void shouldEnqueueNodes() {
        int orderDiscovered = -1;

        SearchNode node1 = new SearchNode(null, ++orderDiscovered, new NodePath(), NodePath.Direction.UP);
        SearchNode node2 = new SearchNode(null, ++orderDiscovered, new NodePath(), NodePath.Direction.UP);
        SearchNode node3 = new SearchNode(null, ++orderDiscovered, new NodePath(), NodePath.Direction.UP);
        SearchNode node4 = new SearchNode(null, ++orderDiscovered, new NodePath(), NodePath.Direction.UP);

        breadthFirstSearch.enqueue(node1);
        breadthFirstSearch.enqueue(node2);
        breadthFirstSearch.enqueue(node3);
        breadthFirstSearch.enqueue(node4);

        assertQueueEqualsToListNodes(breadthFirstSearch, List.of(node1, node2, node3, node4));
    }

    @Test
    void shouldEnqueueNodesWithPathCosts() {
        int orderDiscovered = -1;

        SearchNode node1 = new SearchNode(null, ++orderDiscovered, new NodePath(), NodePath.Direction.UP, 2);
        SearchNode node2 = new SearchNode(null, ++orderDiscovered, new NodePath(), NodePath.Direction.UP, 5);
        SearchNode node3 = new SearchNode(null, ++orderDiscovered, new NodePath(), NodePath.Direction.UP, 3);
        SearchNode node4 = new SearchNode(null, ++orderDiscovered, new NodePath(), NodePath.Direction.UP, 1);

        breadthFirstSearch.enqueue(node1);
        breadthFirstSearch.enqueue(node2);
        breadthFirstSearch.enqueue(node3);
        breadthFirstSearch.enqueue(node4);

        assertQueueEqualsToListNodes(breadthFirstSearch, List.of(node1, node2, node3, node4));
    }
}