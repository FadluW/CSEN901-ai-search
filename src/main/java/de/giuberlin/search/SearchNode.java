package de.giuberlin.search;

import de.giuberlin.search.NodePath.Direction;
import grid.GridObject;

public class SearchNode {
    private final NodePath path;
    private final GridObject gridObject;
    private final int costToExpand; // Keep track of direct cost to expand this node (UniformCost)
    private final int orderDiscovered; // Keep FIFO behaviour in PriorityQueues

    public SearchNode(GridObject discoveredNode, int orderDiscovered, NodePath pathToNode, Direction directionTaken, int cost) {
        this.gridObject = discoveredNode;
        this.path = pathToNode.clone().add(directionTaken, cost);
        this.orderDiscovered = orderDiscovered;
        this.costToExpand = cost;
    }

    public SearchNode(GridObject discoveredNode, int orderDiscovered, NodePath pathToNode, Direction directionTaken) {
        this.gridObject = discoveredNode;
        this.path = pathToNode.clone().add(directionTaken);
        this.orderDiscovered = orderDiscovered;
        this.costToExpand = 0;
    }

    public SearchNode(GridObject startingNode) {
        this.gridObject = startingNode;
        this.path = new NodePath();
        this.orderDiscovered = 0;
        this.costToExpand = 0;
    }

    public int getCostToExpand() {
        return this.costToExpand;
    }

    public NodePath getPath() {
        return this.path;
    }

    public GridObject getGridObject() {
        return this.gridObject;
    }

    public int getOrderDiscovered() {
        return orderDiscovered;
    }
}
