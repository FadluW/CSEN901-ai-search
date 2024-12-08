package de.giuberlin;

import de.giuberlin.exceptions.GoalNotFoundException;
import de.giuberlin.grid.Grid;
import de.giuberlin.grid.types.Customer;
import de.giuberlin.grid.types.Store;
import de.giuberlin.search.NodePath;
import de.giuberlin.search.strategies.InformedSearchStrategy;
import de.giuberlin.search.strategies.IterativeDeepeningSearch;
import de.giuberlin.search.strategies.Strategy;
import de.giuberlin.search.strategies.StrategyCode;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;

public class DeliverySearch extends GenericSearch implements DeliverySearchInterface {
    @Override
    public NodePath path(Store start, Customer goal, Strategy searchStrategy, boolean visualize) {
        if (visualize) {
            grid.clearAllVisitedGridObjects();
            return getVisualizedPathStartingFrom(start, goal, searchStrategy);
        } else {
            return getPathStartingFrom(start, goal, searchStrategy);
        }
    }

    @Override
    public String plan(String initialState, String traffic, String strategy, Boolean visualize) {
        // Create grid to use
        setInitialState(initialState);
        addTraffic(traffic);

        // Map search strategy
        Strategy searchStrategy = StrategyCode.valueOf(strategy.toUpperCase()).getStrategy();

        StringBuilder stringBuilder = new StringBuilder();
        for (Store store : grid.getStores()) {
            for (Customer customer : grid.getCustomers()) {
                NodePath path;
                if (searchStrategy instanceof IterativeDeepeningSearch) {
                    path = iterativeDeepeningPath(store, customer, (IterativeDeepeningSearch) searchStrategy, visualize);
                } else if (searchStrategy instanceof InformedSearchStrategy) {
                    ((InformedSearchStrategy) searchStrategy).initializeWithGoal(customer);
                    path = path(store, customer, searchStrategy, visualize);
                } else {
                    path = path(store, customer, searchStrategy, visualize);
                }

                if (path == null) {
                    throw new RuntimeException("Invalid null path generated!");
                }

                stringBuilder
                        .append(store.getCoords().x).append(",").append(store.getCoords().y).append(";")
                        .append(customer.getCoords().x).append(",").append(customer.getCoords().y).append(";")
                        .append(path).append(";").append(countNodesExpanded)
                        .append("\n");
                searchStrategy.reset();
            }
        }

        return stringBuilder.toString();
    }

    private void setInitialState(String initialState){
        LinkedList<String> parts = new LinkedList<>(Arrays.asList(initialState.split(";")));
        int m = Integer.parseInt(parts.pop());
        int n = Integer.parseInt(parts.pop());
        int p = Integer.parseInt(parts.pop());
        int s = Integer.parseInt(parts.pop());

        grid = new Grid(m, n);
        String customersString = parts.pop();
        addCustomers(customersString, p);

        String storesString = parts.pop();
        addStores(storesString, s);

        String tunnelsString = parts.pop();
        addTunnels(tunnelsString);
    }

    private void addCustomers(String customersString, int numCustomers) {
        LinkedList<String> customersList = new LinkedList<>(Arrays.asList(customersString.split(",")));
        for (int i = 0; i < numCustomers; i++) {
            int x = Integer.parseInt(customersList.pop());
            int y = Integer.parseInt(customersList.pop());
            
            grid.setCustomer(new Point(x, y));
        }
    }

    private void addStores(String storesString, int numStores) {
        LinkedList<String> storesList = new LinkedList<>(Arrays.asList(storesString.split(",")));
        for (int i = 0; i < numStores; i++) {
            int x = Integer.parseInt(storesList.pop());
            int y = Integer.parseInt(storesList.pop());

            grid.setStore(new Point(x, y));
        }
    }

    private void addTunnels(String tunnelsString) {
        LinkedList<String> tunnelsList = new LinkedList<>(Arrays.asList(tunnelsString.split(",")));
        while (!tunnelsList.isEmpty()) {
            int tunnel1x = Integer.parseInt(tunnelsList.pop());
            int tunnel1y = Integer.parseInt(tunnelsList.pop());
            int tunnel2x = Integer.parseInt(tunnelsList.pop());
            int tunnel2y = Integer.parseInt(tunnelsList.pop());
            Point firstTunnelCoords = new Point(tunnel1x, tunnel1y);
            Point secondTunnelCoords = new Point(tunnel2x, tunnel2y);
            
            grid.setTunnelExits(firstTunnelCoords, secondTunnelCoords);
        }
    }

    private void addTraffic(String traffic){
        String[] segments = traffic.split(";");
        for (String segment : segments) {
            String[] segmentValues = segment.split(",");
            int trafficValue = Integer.parseInt(segmentValues[4]);
            if (trafficValue < 1) continue; //do not add segment if traffic value is 0

            Point source = new Point(Integer.parseInt(segmentValues[0]), Integer.parseInt(segmentValues[1]));
            Point destination = new Point(Integer.parseInt(segmentValues[2]), Integer.parseInt(segmentValues[3]));
            grid.setTraffic(source, destination, trafficValue);
        }
    }

    private NodePath iterativeDeepeningPath(Store start, Customer goal, IterativeDeepeningSearch searchStrategy, boolean visualize) {
        int gridNodes = grid.getSize();
        int depth = 1;

        while (depth < gridNodes) {
            try {
                searchStrategy.reset();
                searchStrategy.setCurrentLevel(depth);
                return path(start, goal, searchStrategy, visualize);
            } catch (GoalNotFoundException e) {
                depth++;
            }
        }

        throw new GoalNotFoundException();
    }
}
