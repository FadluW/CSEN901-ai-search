package de.giuberlin.grid;

import de.giuberlin.exceptions.InvalidNeighboursException;
import de.giuberlin.grid.types.Customer;
import de.giuberlin.grid.types.EmptyGridObject;
import de.giuberlin.grid.types.GridObject;
import de.giuberlin.grid.types.Store;
import de.giuberlin.grid.types.Tunnel;
import de.giuberlin.grid.types.traffic.HorizontalTrafficSegment;
import de.giuberlin.grid.types.traffic.VerticalTrafficSegment;
import de.giuberlin.search.NodePath;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
    private GridObject[][] grid;
    private final ArrayList<Store> stores = new ArrayList<>();
    private final ArrayList<Customer> customers = new ArrayList<>();

    public Grid(int width, int height) {
        initializeEmptyGrid(width, height);
    }

    public GridObject getGridObject(Point coords) {
        return grid[coords.y][coords.x];
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public int getSize() {
        return grid.length * grid[0].length;
    }

    public void displayGrid() {
        System.out.println("\nGrid (" + grid[0].length + " x " + grid.length + ") ===========================================\n");

        for (int row = grid.length - 1; row >= 0; row--) {
            displayRow(row);
            System.out.print("\n");
            displayTrafficUnderneath(row);
            System.out.print("\n");
        }
    }

    public void setStore(Point coords) {
        Store store = new Store(coords.x, coords.y);
        stores.add(store);
        setGridObject(store, coords);
    }

    public void setCustomer(Point coords) {
        Customer customer = new Customer(coords.x, coords.y);
        customers.add(customer);
        setGridObject(customer, coords);
    }

    public void setTunnelExits(Point exit1, Point exit2) {
        if (exit1.equals(exit2)) {
            throw new IllegalArgumentException("Tunnel exits cannot be on the same point!");
        }

        Tunnel tunnel1 = new Tunnel(exit1.x, exit1.y);
        Tunnel tunnel2 = tunnel1.createAndLinkOtherExitAt(exit2);

        setGridObject(tunnel1, exit1);
        setGridObject(tunnel2, exit2);
    }

    public void setTraffic(Point source, Point destination, int traffic) {
        if (source.x == destination.x) {
            int heightDiff = source.y - destination.y;

            if (heightDiff == 1) {
                setVerticalNeighbours(grid[source.y][source.x], grid[destination.y][destination.x], traffic);
            } else if (heightDiff == -1) {
                setVerticalNeighbours(grid[destination.y][destination.x], grid[source.y][source.x], traffic);
            } else {
                throw new InvalidNeighboursException(source, destination);
            }

            return;
        }

        if (source.y == destination.y) {
            int lengthDiff = source.x - destination.x;

            if (lengthDiff == 1) {
                setHorizontalNeighbours(grid[source.y][source.x], grid[destination.y][destination.x], traffic);
            } else if (lengthDiff == -1) {
                setHorizontalNeighbours(grid[destination.y][destination.x], grid[source.y][source.x], traffic);
            } else {
                throw new InvalidNeighboursException(source, destination);
            }

            return;
        }

        throw new InvalidNeighboursException(source, destination);
    }

    public void clearAllVisitedGridObjects() {
        Arrays.stream(grid).forEach((gridObjects ->
                Arrays.stream(gridObjects).forEach((gridObject ->
                        gridObject.setIsVisited(false)))));
    }

    private void initializeEmptyGrid(int width, int height) {
        grid = new GridObject[height][width];

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                grid[row][column] = new EmptyGridObject(column, row);
            }
        }        
    }

    private void initalizeAllZeroTraffic(int width, int height) {
        int lastRow = height - 1;
        int lastCol = width - 1;
        for (int row = 0; row < lastRow; row++) {
            for (int column = 0; column < lastCol; column++) {
                setHorizontalNeighbours(grid[row][column + 1], grid[row][column]);
                setVerticalNeighbours(grid[row + 1][column], grid[row][column]);
            }
        }

        for (int row = 0; row < lastRow; row++) {
            setVerticalNeighbours(grid[row + 1][lastCol], grid[row][lastCol]);
        }

        for (int column = 0; column < lastCol; column++) {
            setHorizontalNeighbours(grid[lastRow][column + 1], grid[lastRow][column]);
        }
    }

    private void setVerticalNeighbours(GridObject north, GridObject south) {
        setVerticalNeighbours(north, south, 0);
    }

    private void setVerticalNeighbours(GridObject north, GridObject south, int traffic) {
        VerticalTrafficSegment trafficSegment =
                north.getNeighbours().createSouthNeighbourFromSelf(north, south, traffic);

        south.getNeighbours().setNorthSegment(trafficSegment);
    }

    private void setHorizontalNeighbours(GridObject east, GridObject west) {
        setHorizontalNeighbours(east, west, 0);
    }

    private void setHorizontalNeighbours(GridObject east, GridObject west, int traffic) {
        HorizontalTrafficSegment trafficSegment =
                east.getNeighbours().createWestNeighbourFromSelf(east, west, traffic);

        west.getNeighbours().setEastSegment(trafficSegment);
    }

    private void displayTrafficUnderneath(int row) {
        printVerticalPadding(row);
        System.out.print("\n");
        for (int column = 0; column < grid[row].length; column++) {
            GridObject southNeighbour = grid[row][column].getNeighbours().getNeighbour(NodePath.Direction.DOWN);
            System.out.print(((southNeighbour == null) ? " " : grid[row][column].getNeighbours().getCost(NodePath.Direction.DOWN)) + "             ");
        }
        System.out.print("\n");
        printVerticalPadding(row);
    }

    private void printVerticalPadding(int row) {
        for (int column = 0; column < grid[row].length; column++) {
            GridObject southNeighbour = grid[row][column].getNeighbours().getNeighbour(NodePath.Direction.DOWN);
            System.out.print(((southNeighbour == null) ? " " : "|") + "             ");
        }
    }

    private void displayRow(int row) {
        for (int column = 0; column < grid[row].length; column++) {
            System.out.print(grid[row][column]);

            GridObject southNeighbour = grid[row][column].getNeighbours().getNeighbour(NodePath.Direction.RIGHT);
            if (southNeighbour == null) {
                System.out.print("             ");
            } else {
                System.out.print("  --  " + grid[row][column].getNeighbours().getCost(NodePath.Direction.RIGHT) + "  --  ");
            }
        }
    }

    private void assertGridLocationEmpty(Point coords) {
        if (!(grid[coords.y][coords.x] instanceof EmptyGridObject)) {
            throw new IllegalArgumentException("Grid coordinates (" + coords.x + ", " + coords.y + ") already occupied");
        }
    }

    private void assertWithinGridBounds(Point coords) {
        if (coords.y >= grid.length || coords.x >= grid[0].length) {
            throw new IllegalArgumentException("Grid coordinates (" + coords.x + ", " + coords.y + ") out of bounds");
        }
    }

    private void setGridObject(GridObject obj, Point coords) {
        assertWithinGridBounds(coords);
        assertGridLocationEmpty(coords);

        grid[coords.y][coords.x] = obj;
    }
}
