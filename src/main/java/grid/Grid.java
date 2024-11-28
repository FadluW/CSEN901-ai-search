package grid;

import grid.types.EmptyGridObject;
import grid.types.GridObject;

import java.awt.*;

public class Grid {
    private GridObject[][] grid;

    public Grid() {}

    private void initializeEmptyGrid(int width, int height) {
        grid = new GridObject[height][width];

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                grid[row][column] = new EmptyGridObject(row, column);
            }
        }
    }

    public GridObject getGridObject(Point coords) {
        return grid[coords.x][coords.y];
    }
}
