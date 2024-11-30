package grid.types.traffic;

import de.giuberlin.exceptions.InvalidNeighboursException;
import de.giuberlin.grid.types.traffic.GridNeighbours;
import de.giuberlin.search.NodePath;
import de.giuberlin.grid.types.EmptyGridObject;
import de.giuberlin.grid.types.GridObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GridNeighboursTest {
    final static GridObject mainTestNode = new EmptyGridObject(1, 1);
    final static GridObject upTestNode = new EmptyGridObject(1, 2);
    final static GridObject rightTestNode = new EmptyGridObject(2, 1);
    final static GridObject leftTestNode = new EmptyGridObject(0, 1);
    final static GridObject downTestNode = new EmptyGridObject(1, 0);
    final static GridObject farTestNode = new EmptyGridObject(3, 1);

    @Test
    void properlySetsAndGetsAllNeighbours() {
        GridNeighbours testNeighbours = new GridNeighbours();
        testNeighbours.createNorthNeighbourFromSelf(mainTestNode, upTestNode, 1);
        testNeighbours.createEastNeighbourFromSelf(mainTestNode, rightTestNode, 2);
        testNeighbours.createWestNeighbourFromSelf(mainTestNode, leftTestNode, 3);
        testNeighbours.createSouthNeighbourFromSelf(mainTestNode, downTestNode, 4);

        assertEquals(upTestNode, testNeighbours.getNeighbour(NodePath.Direction.UP));
        assertEquals(downTestNode, testNeighbours.getNeighbour(NodePath.Direction.DOWN));
        assertEquals(leftTestNode, testNeighbours.getNeighbour(NodePath.Direction.LEFT));
        assertEquals(rightTestNode, testNeighbours.getNeighbour(NodePath.Direction.RIGHT));
    }

    @Test
    void failsOnSettingInvalidNeighbours() {
        GridNeighbours testNeighbours = new GridNeighbours();

        assertThrows(InvalidNeighboursException.class, () ->
                testNeighbours.createSouthNeighbourFromSelf(mainTestNode, farTestNode, 1));
        assertThrows(InvalidNeighboursException.class, () ->
                testNeighbours.createSouthNeighbourFromSelf(mainTestNode, upTestNode, 1));
        assertThrows(InvalidNeighboursException.class, () ->
                testNeighbours.createSouthNeighbourFromSelf(mainTestNode, rightTestNode, 1));
        assertThrows(InvalidNeighboursException.class, () ->
                testNeighbours.createSouthNeighbourFromSelf(mainTestNode, leftTestNode, 1));


        assertThrows(InvalidNeighboursException.class, () ->
                testNeighbours.createNorthNeighbourFromSelf(mainTestNode, farTestNode, 1));
        assertThrows(InvalidNeighboursException.class, () ->
                testNeighbours.createNorthNeighbourFromSelf(mainTestNode, rightTestNode, 1));
        assertThrows(InvalidNeighboursException.class, () ->
                testNeighbours.createNorthNeighbourFromSelf(mainTestNode, leftTestNode, 1));
        assertThrows(InvalidNeighboursException.class, () ->
                testNeighbours.createNorthNeighbourFromSelf(mainTestNode, downTestNode, 1));


        assertThrows(InvalidNeighboursException.class, () ->
                testNeighbours.createEastNeighbourFromSelf(mainTestNode, farTestNode, 1));
        assertThrows(InvalidNeighboursException.class, () ->
                testNeighbours.createEastNeighbourFromSelf(mainTestNode, upTestNode, 1));
        assertThrows(InvalidNeighboursException.class, () ->
                testNeighbours.createEastNeighbourFromSelf(mainTestNode, leftTestNode, 1));
        assertThrows(InvalidNeighboursException.class, () ->
                testNeighbours.createEastNeighbourFromSelf(mainTestNode, downTestNode, 1));


        assertThrows(InvalidNeighboursException.class, () ->
                testNeighbours.createWestNeighbourFromSelf(mainTestNode, farTestNode, 1));
        assertThrows(InvalidNeighboursException.class, () ->
                testNeighbours.createWestNeighbourFromSelf(mainTestNode, upTestNode, 1));
        assertThrows(InvalidNeighboursException.class, () ->
                testNeighbours.createWestNeighbourFromSelf(mainTestNode, rightTestNode, 1));
        assertThrows(InvalidNeighboursException.class, () ->
                testNeighbours.createWestNeighbourFromSelf(mainTestNode, downTestNode, 1));

    }
}