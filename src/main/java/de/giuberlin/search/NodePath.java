package de.giuberlin.search;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class NodePath implements Cloneable {
    private ArrayList<Direction> plan = new ArrayList<>();
    private long cost = 0;

    NodePath() {}

    private NodePath(ArrayList<Direction> plan, long cost) {
        this.plan = plan;
        this.cost = cost;
    }

    public NodePath add(Direction direction, Integer cost) {
        this.plan.add(direction);
        this.cost += cost;
        return this;
    }
    
    public NodePath add(Direction direction) {
        return this.add(direction, 0);
    }

    @Override
    public String toString() {
        return plan.stream().map(Direction::toString).collect(Collectors.joining(",")) + ";" + 
            cost + ";" + 
            plan.size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public NodePath clone() {
        return new NodePath((ArrayList<Direction>) this.plan.clone(), cost);
    }

    public enum Direction {
        UP("up"),
        DOWN("down"),
        LEFT("left"),
        RIGHT("right"),
        TUNNEL("tunnel");

        
        private final String stringValue;
        Direction(String s) {
            this.stringValue = s;
        }

        @Override
        public String toString() {
            return this.stringValue;
        }
    }
}
