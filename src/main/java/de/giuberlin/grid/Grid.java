package de.giuberlin.grid;

import de.giuberlin.exceptions.InvalidNeighboursException;
import de.giuberlin.grid.types.EmptyGridObject;
import de.giuberlin.grid.types.GridObject;
import de.giuberlin.grid.types.traffic.HorizontalTrafficSegment;
import de.giuberlin.grid.types.traffic.VerticalTrafficSegment;
import de.giuberlin.search.NodePath;

import java.awt.*;

public class Grid {
    private GridObject[][] grid;

    public Grid(int width, int height) {
        initializeEmptyGrid(width, height);
    }

    public GridObject getGridObject(Point coords) {
        return grid[coords.x][coords.y];
    }

    public void setGridObject(Point coords, GridObject gridObject){
        grid[coords.x][coords.y] = gridObject;
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

    private void initializeEmptyGrid(int width, int height) {
        grid = new GridObject[height][width];

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                grid[row][column] = new EmptyGridObject(column, row);
            }
        }

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

    public void displayGrid() {
        System.out.println("\nGrid (" + grid[0].length + " x " + grid.length + ") ===========================================\n");

        for (int row = grid.length - 1; row >= 0; row--) {
            displayRow(row);
            System.out.print("\n");
            displayTrafficUnderneath(row);
            System.out.print("\n");
        }
    }

    private void displayTrafficUnderneath(int row) {
        printVerticalPadding(row);
        System.out.print("\n");
        for (int column = 0; column < grid[row].length; column++) {
            GridObject southNeighbour = grid[row][column].getNeighbours().getNeighbour(NodePath.Direction.DOWN);
            System.out.print(((southNeighbour == null) ? " " : grid[row][column].getNeighbours().getCost(NodePath.Direction.DOWN)) + "           ");
        }
        System.out.print("\n");
        printVerticalPadding(row);
    }

    private void printVerticalPadding(int row) {
        for (int column = 0; column < grid[row].length; column++) {
            GridObject southNeighbour = grid[row][column].getNeighbours().getNeighbour(NodePath.Direction.DOWN);
            System.out.print(((southNeighbour == null) ? " " : "|") + "           ");
        }
    }

    private void displayRow(int row) {
        for (int column = 0; column < grid[row].length; column++) {
            System.out.print(grid[row][column]);

            GridObject southNeighbour = grid[row][column].getNeighbours().getNeighbour(NodePath.Direction.RIGHT);
            if (southNeighbour == null) {
                System.out.print("          ");
            } else {
                System.out.print(" --- " + grid[row][column].getNeighbours().getCost(NodePath.Direction.RIGHT) + " --- ");
            }
        }
    }
}
