package de.giuberlin;

import de.giuberlin.grid.Grid;
import de.giuberlin.grid.types.Customer;
import de.giuberlin.grid.types.GridObject;
import de.giuberlin.grid.types.Store;
import de.giuberlin.grid.types.Tunnel;
import de.giuberlin.search.strategies.Strategy;
import de.giuberlin.search.strategies.StrategyCode;

import java.awt.*;
import java.util.*;

public class DeliverySearch extends GenericSearch implements DeliverySearchInterface {

    Grid grid;
    @Override
    public String GenGrid() {
        Random rnd = new Random();
        int width = rnd.nextInt(4, 11), height = rnd.nextInt(4, 11);
        String initialState = genInitialState(width, height);
        String traffic = genTraffic(width, height);

        // TODO check whether this method should return initialState or traffic
        throw new UnsupportedOperationException("Unimplemented method 'GenGrid'");

    }

    private String genInitialState(int m, int n){
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

        StringBuilder initialState = new StringBuilder();
        initialState.append(initialValues).append(customerPositions).append(storePositions).append(tunnelsPositions);
        return initialState.toString();
    }

    private Point findUnoccupiedPosition(int width, int height, HashSet<Point> occupiedPositions){
        Random rnd = new Random();
        int x = 0, y = 0;
        do {
            x = rnd.nextInt(0, width);
            y = rnd.nextInt(0, height);
        } while (occupiedPositions.contains(new Point(x, y)));
        return new Point(x, y);
    }

    private String genTraffic(int width, int height){
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

    @Override
    public String path(Strategy searchStrategy, GridObject start, GridObject goal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'path'");
    }

    @Override
    public String plan(String initialState, String traffic, String strategy, Boolean visualize) {
        // Create grid to use
        setInitialState(initialState);
        addTraffic(traffic);

        // Map search strategy
        Strategy searchStrategy = StrategyCode.valueOf(strategy.toUpperCase()).getStrategy();

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'plan'");
    }

    private void setInitialState(String initialState){
        LinkedList<String> parts = new LinkedList<>(Arrays.asList(initialState.split(";")));
        int m = Integer.parseInt(parts.pop());
        int n = Integer.parseInt(parts.pop());
        int p = Integer.parseInt(parts.pop());
        int s = Integer.parseInt(parts.pop());

        grid = new Grid(m, n);
        String customersString = parts.pop();
        LinkedList<String> customersList = new LinkedList<>(Arrays.asList(customersString.split(",")));
        for (int i = 0; i < p; i++) {
            int x = Integer.parseInt(customersList.pop());
            int y = Integer.parseInt(customersList.pop());
            Customer customer = new Customer(x, y);
            grid.setGridObject(new Point(x, y), customer);
        }

        String storesString = parts.pop();
        LinkedList<String> storesList = new LinkedList<>(Arrays.asList(storesString.split(",")));
        for (int i = 0; i < s; i++) {
            int x = Integer.parseInt(storesList.pop());
            int y = Integer.parseInt(storesList.pop());
            Store store = new Store(x, y);
            grid.setGridObject(new Point(x, y), store);
        }

        String tunnelsString = parts.pop();
        LinkedList<String> tunnelsList = new LinkedList<>(Arrays.asList(tunnelsString.split(",")));
        while (!tunnelsList.isEmpty()) {
            int tunnel1x = Integer.parseInt(tunnelsList.pop());
            int tunnel1y = Integer.parseInt(tunnelsList.pop());
            int tunnel2x = Integer.parseInt(tunnelsList.pop());
            int tunnel2y = Integer.parseInt(tunnelsList.pop());
            Tunnel firstTunnel = new Tunnel(tunnel1x, tunnel1y);
            Tunnel secondTunnel = new Tunnel(tunnel2x, tunnel2y);
            firstTunnel.assignOtherEnd(secondTunnel);
            secondTunnel.assignOtherEnd(firstTunnel);

            grid.setGridObject(new Point(tunnel1x, tunnel1y), firstTunnel);
            grid.setGridObject(new Point(tunnel2x, tunnel2y), secondTunnel);
        }

    }

    private void addTraffic(String traffic){
        String[] segments = traffic.split(";");
        for (String segment : segments) {
            String[] segmentValues = segment.split(",");
            int trafficValue = Integer.parseInt(segmentValues[4]);
            if (trafficValue < 1)
                continue; //do not add segment if traffic value is 0

            Point source = new Point(Integer.parseInt(segmentValues[0]), Integer.parseInt(segmentValues[1]));
            Point destination = new Point(Integer.parseInt(segmentValues[2]), Integer.parseInt(segmentValues[3]));
            grid.setTraffic(source, destination, trafficValue);
        }
    }


}
