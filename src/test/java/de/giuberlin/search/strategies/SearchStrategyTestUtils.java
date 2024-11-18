package de.giuberlin.search.strategies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import de.giuberlin.search.SearchNode;

public class SearchStrategyTestUtils {
    public static void assertQueueEqualsToListCosts(Strategy strategy, List<Integer> listCosts) {
        for(Integer cost : listCosts) {
            assertEquals(cost, strategy.dequeue().getCostToExpand());
        }

        assertEquals(null, strategy.dequeue());
    }

    public static void assertQueueEqualsToListNodes(Strategy strategy, List<SearchNode> listNodes) {
        for(SearchNode node : listNodes) {
            assertEquals(node, strategy.dequeue());
        }

        assertEquals(null, strategy.dequeue());
    }
}
