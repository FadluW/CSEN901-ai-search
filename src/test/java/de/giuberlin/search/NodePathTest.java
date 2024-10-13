package de.giuberlin.search;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import de.giuberlin.search.NodePath.Direction;

public class NodePathTest {

    @Test
    public void shouldAddZeroCostNodeAndPrintCorrectly() {
        NodePath testPath = new NodePath();
        testPath.add(Direction.DOWN);

        assertThat(testPath.toString(), is("down;0;1"));
    }
    
    @Test
    public void shouldAddMultipleZeroCostNodesAndPrintCorrectly() {
        NodePath testPath = new NodePath();
        testPath.add(Direction.DOWN);
        testPath.add(Direction.UP);
        testPath.add(Direction.RIGHT);
        testPath.add(Direction.LEFT);

        assertThat(testPath.toString(), is("down,up,right,left;0;4"));
    }
    
    @Test
    public void shouldAddWeightedNodeAndPrintCorrectly() {
        NodePath testPath = new NodePath();
        testPath.add(Direction.RIGHT, 3);

        assertThat(testPath.toString(), is("right;3;1"));
    }
    
    @Test
    public void shouldAddMultipleWeightedNodesAndPrintCorrectly() {
        NodePath testPath = new NodePath();
        testPath.add(Direction.DOWN, 2);
        testPath.add(Direction.UP, 4);
        testPath.add(Direction.RIGHT, 1);
        testPath.add(Direction.LEFT, 1);

        assertThat(testPath.toString(), is("down,up,right,left;8;4"));
    }
}