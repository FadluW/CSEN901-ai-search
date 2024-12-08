package de.giuberlin.grid;

import de.giuberlin.exceptions.InvalidNeighboursException;
import de.giuberlin.grid.types.Customer;
import de.giuberlin.grid.types.GridObject;
import de.giuberlin.grid.types.Store;
import de.giuberlin.grid.types.Tunnel;
import de.giuberlin.search.NodePath;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    @Disabled
    void printsEmptyGrid() {
        Grid testGrid = new Grid(5, 6);
        testGrid.displayGrid();
    }

    @Test
    void setTrafficFailsOnFarPoints() {
        Grid testGrid = new Grid(3, 3);
        assertThrows(InvalidNeighboursException.class,
                () -> testGrid.setTraffic(new Point(0, 0), new Point(2, 2), 3));
    }

    @Test
    void setTrafficFailsSamePoint() {
        Grid testGrid = new Grid(3, 3);
        assertThrows(InvalidNeighboursException.class,
                () -> testGrid.setTraffic(new Point(1, 1), new Point(1, 1), 3));
    }

    @Test
    void setStoreAtPoint() {
        Grid testGrid = new Grid(3, 3);
        testGrid.setStore(new Point(1, 1));
        assertInstanceOf(Store.class, testGrid.getGridObject(new Point(1, 1)));
    }

    @Test
    void setCustomerAtPoint() {
        Grid testGrid = new Grid(3, 3);
        testGrid.setCustomer(new Point(1, 1));
        assertInstanceOf(Customer.class, testGrid.getGridObject(new Point(1, 1)));
    }

    @Test
    void setTunnelsAtPoints() {
        Grid testGrid = new Grid(3, 3);
        testGrid.setTunnelExits(new Point(1, 0), new Point(2, 2));
        assertInstanceOf(Tunnel.class, testGrid.getGridObject(new Point(1, 0)));
        assertInstanceOf(Tunnel.class, testGrid.getGridObject(new Point(2, 2)));
    }

    @Test
    void connectTwoNeighbors() {
        Grid testGrid = new Grid(3, 3);
        testGrid.setTraffic(new Point(1, 0), new Point(1, 1), 3);
        GridObject obj1 = testGrid.getGridObject(new Point(0, 1));
        GridObject obj2 = testGrid.getGridObject(new Point(1, 1));
        testGrid.displayGrid();
        assertEquals(obj1, obj2.getNeighbours().getNeighbour(NodePath.Direction.DOWN));
        assertEquals(obj2, obj1.getNeighbours().getNeighbour(NodePath.Direction.UP));
    }

    @Test
    @Disabled
    void printsGridWithTraffic() {
        Grid testGrid = new Grid(3, 3);
        testGrid.setCustomer(new Point(1, 1));
        testGrid.setStore(new Point(2, 2));
        testGrid.setTraffic(new Point(0, 1), new Point(0, 0), 3);
        testGrid.setTraffic(new Point(1, 1), new Point(2, 1), 5);

        testGrid.displayGrid();
    }
}