package de.giuberlin;

import de.giuberlin.exceptions.GoalNotFoundException;
import de.giuberlin.grid.Grid;
import de.giuberlin.search.NodePath;
import de.giuberlin.search.SearchNode;
import de.giuberlin.search.strategies.Strategy;
import de.giuberlin.grid.types.GridObject;
import de.giuberlin.grid.types.Tunnel;

public abstract class GenericSearch {
    protected Grid grid;
    protected int countNodesExpanded = 0;
    private int countNodesVisited;

    public NodePath getPathStartingFrom(GridObject startingCell, GridObject goalCell, Strategy searchStrategy) {
        SearchNode nodeToExpand = new SearchNode(startingCell);
        searchStrategy.enqueue(nodeToExpand);
        countNodesVisited = 1;

        while((nodeToExpand = searchStrategy.dequeue()) != null) {
            if (nodeToExpand.getGridObject() == goalCell) {
                return nodeToExpand.getPath();
            }

            expandNode(nodeToExpand, searchStrategy);
        }

        throw new GoalNotFoundException();
    }

    public NodePath getVisualizedPathStartingFrom(GridObject startingCell, GridObject goalCell, Strategy searchStrategy) {
        SearchNode nodeToExpand = new SearchNode(startingCell);
        searchStrategy.enqueue(nodeToExpand);
        countNodesVisited = 1;
        grid.displayGrid();
        searchStrategy.displayQueue();

        while((nodeToExpand = searchStrategy.dequeue()) != null) {
            if (nodeToExpand.getGridObject() == goalCell) {
                return nodeToExpand.getPath();
            }

            expandNode(nodeToExpand, searchStrategy);
            grid.displayGrid();
            searchStrategy.displayQueue();
        }

        throw new GoalNotFoundException();
    }

    private void expandNode(SearchNode node, Strategy searchStrategy) {
        countNodesExpanded++;
        NodePath pathToNode = node.getPath();
        node.getGridObject().setIsVisited(true);

        // TODO: Ensure all search strategies skip if visited already
        if (node.getGridObject() instanceof Tunnel) {
            // Expand Tunnel
            Tunnel tunnel = ((Tunnel) node.getGridObject()).getOtherExit();
            SearchNode TunnelNode = new SearchNode(tunnel, countNodesVisited++, pathToNode, NodePath.Direction.TUNNEL, tunnel.getCost());
            searchStrategy.enqueue(TunnelNode);
        }

        NodePath.Direction[] directions = {NodePath.Direction.UP, NodePath.Direction.RIGHT, NodePath.Direction.DOWN, NodePath.Direction.LEFT};
        for (NodePath.Direction direction : directions) {
            GridObject nodeToExpand = node.getGridObject().getNeighbours().getNeighbour(direction);
            if (nodeToExpand == null) continue; // Obstruction

            int cost = node.getGridObject().getNeighbours().getCost(direction);

            searchStrategy.enqueue(new SearchNode(nodeToExpand, countNodesVisited++, pathToNode, direction, cost));
        }
    }
}
