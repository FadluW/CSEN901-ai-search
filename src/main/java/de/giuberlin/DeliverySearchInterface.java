package de.giuberlin;

import de.giuberlin.grid.types.Customer;
import de.giuberlin.grid.types.Store;
import de.giuberlin.search.NodePath;
import de.giuberlin.search.strategies.Strategy;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;

public interface DeliverySearchInterface {
    /**
     * Generates a random grid with at least one customer and one store.
     * Where: 1 <= P <= 10 and 1 <= S <= 3
     *
     * @return String representation of the grid ; separated.
     */
    static String GenGrid() {
        Random rnd = new Random();
        int width = rnd.nextInt(4, 11), height = rnd.nextInt(4, 11);
        String initialState = genInitialState(width, height);
        String traffic = genTraffic(width, height);

        return initialState + "-" + traffic;
    }

    /**
     * Uses the given search strategy to find the path for a truck to reach a given destination.
     * 
     * @return {@link de.giuberlin.search.NodePath NodePath}
     */
    NodePath path(Store start, Customer goal, Strategy searchStrategy, boolean visualize);
    
    
    /**
     * Finds the final plan including which truck will deliver which product.
     * 
     * @param initialState String representation of how the grid looks like initially
     * @param traffic String representation of the traffic between segments in the city 
     * @param strategy Search strategy to be applied
     * @param visualize Boolean to toggle displaying pathing in the console
     * @return String of pairs for each store and destination
     */
    String plan(
        String initialState, 
        String traffic, 
        String strategy, 
        Boolean visualize);

    static String genInitialState(int m, int n){
        Random rnd = new Random();
        int p = rnd.nextInt(1, 11);
        int s = rnd.nextInt(1, 4);
        int noOfTunnels = rnd.nextInt(1, 4);

        HashSet<Point> occupiedPositions = new HashSet<>();
        occupiedPositions.add(new Point(0, 0));
        String initialValues = m + ";" + n + ";" + p + ";" + s + ";";
        //Customers
        StringBuilder customerPositions = new StringBuilder();
        for (int i = 0; i < p; i++) {
            Point position = findUnoccupiedPosition(m, n, occupiedPositions);
            occupiedPositions.add(position);
            customerPositions.append(position.x).append(",").append(position.y).append(",");
        }
        customerPositions.deleteCharAt(customerPositions.length() - 1); //removes extra ',' at the end
        customerPositions.append(";"); //adds ; instead to signify the end of customer input
        //Stores
        StringBuilder storePositions = new StringBuilder();
        for (int i = 0; i < s; i++) {
            Point position = findUnoccupiedPosition(m, n, occupiedPositions);
            occupiedPositions.add(position);
            storePositions.append(position.x).append(",").append(position.y).append(",");
        }
        storePositions.deleteCharAt(storePositions.length() - 1); //removes extra ',' at the end
        storePositions.append(";"); //adds ; instead to signify the end of store input
        //Tunnels
        StringBuilder tunnelsPositions = new StringBuilder();
        for (int i = 0; i < noOfTunnels; i++) {
            Point position1 = findUnoccupiedPosition(m, n, occupiedPositions);
            occupiedPositions.add(position1);
            Point position2 = findUnoccupiedPosition(m, n, occupiedPositions);
            occupiedPositions.add(position2);

            tunnelsPositions.append(position1.x).append(",").append(position1.y).append(",");
            tunnelsPositions.append(position2.x).append(",").append(position2.y).append(",");
        }
        tunnelsPositions.deleteCharAt(storePositions.length() - 1); //removes extra ',' at the end
        tunnelsPositions.append(";"); //adds ; instead to signify the end of tunnel input

        return initialValues + customerPositions + storePositions + tunnelsPositions;
    }

    private static Point findUnoccupiedPosition(int width, int height, HashSet<Point> occupiedPositions){
        Random rnd = new Random();
        int x, y;
        do {
            x = rnd.nextInt(0, width);
            y = rnd.nextInt(0, height);
        } while (occupiedPositions.contains(new Point(x, y)));
        return new Point(x, y);
    }

    private static String genTraffic(int width, int height){
        StringBuilder traffic = new StringBuilder();
        int minTraffic = 0, maxTraffic = 4;
        Random rnd = new Random();
        //horizontal segments
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width - 1; column++) {
                int trafficValue = rnd.nextInt(minTraffic, maxTraffic + 1);
                traffic.append(column)
                        .append(",")
                        .append(row)
                        .append(",")
                        .append(column + 1)
                        .append(",")
                        .append(row)
                        .append(",")
                        .append(trafficValue)
                        .append(",");
            }
        }

        //vertical segments
        for (int row = 0; row < height - 1; row++) {
            for (int column = 0; column < width; column++) {
                int trafficValue = rnd.nextInt(minTraffic, maxTraffic + 1);
                traffic.append(column)
                        .append(",")
                        .append(row)
                        .append(",")
                        .append(column)
                        .append(",")
                        .append(row + 1)
                        .append(",")
                        .append(trafficValue)
                        .append(",");
            }
        }
        return traffic.toString();
    }
}
