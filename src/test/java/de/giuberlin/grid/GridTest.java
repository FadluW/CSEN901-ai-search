package de.giuberlin.grid;

import de.giuberlin.exceptions.InvalidNeighboursException;
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
    @Disabled
    void printsGridWithTraffic() {
        Grid testGrid = new Grid(3, 3);
        testGrid.setTraffic(new Point(0, 1), new Point(0, 0), 3);
        testGrid.setTraffic(new Point(1, 1), new Point(2, 1), 5);
        testGrid.setCustomer(new Point(1, 1));
        testGrid.setStore(new Point(2, 2));

        testGrid.displayGrid();
    }
}