package de.giuberlin.search;

import de.giuberlin.search.NodePath.Direction;
import grid.GridObject;

public class SearchNode {
    private NodePath path;
    private GridObject gridObject;

    public SearchNode(GridObject discoveredNode, NodePath pathToNode, Direction directionTaken, int cost) {
        this.gridObject = discoveredNode;
        this.path = pathToNode.clone().add(directionTaken, cost);
    }

    public SearchNode(GridObject discoveredNode, NodePath pathToNode, Direction directionTaken) {
        this.gridObject = discoveredNode;
        this.path = pathToNode.clone().add(directionTaken);
    }

    public SearchNode(GridObject startingNode) {
        this.gridObject = startingNode;
        this.path = new NodePath();
    }

    public NodePath getPath() {
        return this.path;
    }

    public GridObject getGridObject() {
        return this.gridObject;
    }
}
