package de.giuberlin.search.strategies;

import static de.giuberlin.search.strategies.SearchStrategyTestUtils.assertQueueEqualsToListNodes;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import de.giuberlin.search.NodePath;
import de.giuberlin.search.SearchNode;
import de.giuberlin.search.NodePath.Direction;

public class UniformCostSearchTest {
    Strategy uniformCostSearch;

    @BeforeEach
    void setupStrategy() {
        uniformCostSearch = new UniformCostSearch();
    }

    @Test
    void shouldEnqueueNodesWithLongPaths() {
        int orderDiscovered = -1;
        NodePath path1 = new NodePath();
        path1.add(Direction.DOWN, 7);
        NodePath path2 = new NodePath();
        path2.add(Direction.RIGHT, 2);
        path2.add(Direction.UP, 4);


        SearchNode node1 = new SearchNode(null, ++orderDiscovered, path1, Direction.UP, 1); // 8
        SearchNode node2 = new SearchNode(null, ++orderDiscovered, node1.getPath(), Direction.UP, 2); // 10
        SearchNode node3 = new SearchNode(null, ++orderDiscovered, path2, Direction.UP, 2); // 8
        SearchNode node4 = new SearchNode(null, ++orderDiscovered, node3.getPath(), Direction.UP, 3); // 11

        uniformCostSearch.enqueue(node1);
        uniformCostSearch.enqueue(node2);
        uniformCostSearch.enqueue(node3);
        uniformCostSearch.enqueue(node4);

        assertQueueEqualsToListNodes(uniformCostSearch, List.of(node1, node3, node2, node4));
    }

    @Test
    void shouldEnqueueNodesOfEqualCost() {
        int orderDiscovered = -1;
        SearchNode node1 = new SearchNode(null, ++orderDiscovered, new NodePath(), Direction.UP, 2);
        SearchNode node2 = new SearchNode(null, ++orderDiscovered, new NodePath(), Direction.UP, 2);
        SearchNode node3 = new SearchNode(null, ++orderDiscovered, new NodePath(), Direction.UP, 2);
        SearchNode node4 = new SearchNode(null, ++orderDiscovered, new NodePath(), Direction.UP, 2);

        uniformCostSearch.enqueue(node1);
        uniformCostSearch.enqueue(node2);
        uniformCostSearch.enqueue(node3);
        uniformCostSearch.enqueue(node4);

        assertQueueEqualsToListNodes(uniformCostSearch, List.of(node1, node2, node3, node4));
    }

    @Test
    void shouldEnqueueNodesOfAscendingCost() {
        int orderDiscovered = -1;
        SearchNode node1 = new SearchNode(null, ++orderDiscovered, new NodePath(), Direction.UP, 1);
        SearchNode node2 = new SearchNode(null, ++orderDiscovered, new NodePath(), Direction.UP, 2);
        SearchNode node3 = new SearchNode(null, ++orderDiscovered, new NodePath(), Direction.UP, 3);
        SearchNode node4 = new SearchNode(null, ++orderDiscovered, new NodePath(), Direction.UP, 4);

        uniformCostSearch.enqueue(node1);
        uniformCostSearch.enqueue(node2);
        uniformCostSearch.enqueue(node3);
        uniformCostSearch.enqueue(node4);

        assertQueueEqualsToListNodes(uniformCostSearch, List.of(node1, node2, node3, node4));
    }

    @Test
    void shouldEnqueueNodesOfDescendingCost() {
        int orderDiscovered = -1;
        SearchNode node1 = new SearchNode(null, ++orderDiscovered, new NodePath(), Direction.UP, 4);
        SearchNode node2 = new SearchNode(null, ++orderDiscovered, new NodePath(), Direction.UP, 3);
        SearchNode node3 = new SearchNode(null, ++orderDiscovered, new NodePath(), Direction.UP, 2);
        SearchNode node4 = new SearchNode(null, ++orderDiscovered, new NodePath(), Direction.UP, 1);

        uniformCostSearch.enqueue(node1);
        uniformCostSearch.enqueue(node2);
        uniformCostSearch.enqueue(node3);
        uniformCostSearch.enqueue(node4);

        assertQueueEqualsToListNodes(uniformCostSearch, List.of(node4, node3, node2, node1));
    }
}
