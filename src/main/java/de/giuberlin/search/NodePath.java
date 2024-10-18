package de.giuberlin.search;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class NodePath {
    private ArrayList<Direction> plan = new ArrayList<>();
    private long cost = 0;

    NodePath() {}

    public void add(Direction direction, int cost) {
        this.plan.add(direction);
        this.cost += cost;
    }
    
    public void add(Direction direction) {
        this.add(direction, 0);
    }

    @Override
    public String toString() {
        return plan.stream().map(Object::toString).collect(Collectors.joining(",")) + ";" + 
            cost + ";" + 
            plan.size();
    }

    public enum Direction {
        UP("up"),
        DOWN("down"),
        LEFT("left"),
        RIGHT("right");

        
        private String stringValue;
        Direction(String s) {
            this.stringValue = s;
        }

        @Override
        public String toString() {
            return this.stringValue;
        }
    }
}
