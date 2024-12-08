package de.giuberlin;

import de.giuberlin.search.strategies.StrategyCode;

import java.awt.*;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;

public class DeliveryPlanner {
    private final DeliverySearch deliverySearch;
    private final String initialState;
    private final String traffic;
    private String strategy;
    private final boolean visualize;

    private static class PlanResult {
        final Point storePoint;
        final Point customerPoint;
        final String pathDetails;
        final int nodesExpanded;
        final int cost;

        private PlanResult(Point storePoint, Point customerPoint, String pathDetails, int nodesExpanded, int cost) {
            this.storePoint = storePoint;
            this.customerPoint = customerPoint;
            this.pathDetails = pathDetails;
            this.nodesExpanded = nodesExpanded;
            this.cost = cost;
        }

        public static PlanResult parse(String toParse) {
            String[] planParts = toParse.split(";");
            String[] storeXY = planParts[0].split(",");
            String[] customerXY = planParts[1].split(",");

            Point storePoint = new Point(Integer.parseInt(storeXY[0]), Integer.parseInt(storeXY[1]));
            Point customerPoint = new Point(Integer.parseInt(customerXY[0]), Integer.parseInt(customerXY[1]));
            String pathDetails = planParts[2];
            int nodesExpanded = Integer.parseInt(planParts[4]);
            int cost = Integer.parseInt(planParts[3]);

            return new PlanResult(storePoint, customerPoint, pathDetails, nodesExpanded, cost);
        }

        @Override
        public String toString() {
            return "Customer: (" + customerPoint.x + ", " + customerPoint.y + ")" +
                    "\nStore: (" + storePoint.x + ", " + storePoint.y + ")" +
                    "\nCost: " + cost +
                    "\nRoute: " + pathDetails +
                    "\nNodes Expanded: " + nodesExpanded +
                    "\n";
        }
    }

    private DeliveryPlanner(String initialState, String traffic, String strategy, boolean visualize) {
        this.deliverySearch = new DeliverySearch();
        this.initialState = initialState;
        this.traffic = traffic;
        this.strategy = strategy;
        this.visualize = visualize;
    }

    private DeliveryPlanner(String initialState, String traffic, boolean visualize) {
        this.deliverySearch = new DeliverySearch();
        this.initialState = initialState;
        this.traffic = traffic;
        this.strategy = null;
        this.visualize = visualize;
    }

    public void execute() {
        if (strategy != null) {
            parseAndDisplayPlanResult(planWithStrategy(strategy));
        }
        else
            Arrays.stream(StrategyCode.values())
                .map(StrategyCode::toString)
                .map(this::planWithStrategy)
                .forEach(this::parseAndDisplayPlanResult);
    }

    public void measureAndExecute(){
        for(StrategyCode code : StrategyCode.values()) {
            System.gc();
            long beforeUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
            threadMXBean.setThreadCpuTimeEnabled(true);
            threadMXBean.setThreadContentionMonitoringEnabled(true);
            Instant before = Instant.now();

            this.strategy = code.toString();
            execute();
            Instant after = Instant.now();

            long afterUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long memoryUsed = afterUsedMemory - beforeUsedMemory;
            System.out.println("Memory used: " + memoryUsed + " bytes");
            System.out.println("Elapsed Time: "+ Duration.between(before, after).getNano() / 1000000 + " ms");
            System.out.println();
        }
    }

    public static DeliveryPlanner withRandomGrid() {
       String[] gridString = DeliverySearchInterface.GenGrid().split("-");
       return new DeliveryPlanner(gridString[0], gridString[1], false);
    }

    public static DeliveryPlanner withGrid(String initialState, String traffic) {
        return new DeliveryPlanner(initialState, traffic, false);
    }

    private String planWithStrategy(String strategy) {
        System.out.println("Starting plan for strategy: " + strategy + "\n==============");
        return this.deliverySearch.plan(initialState, traffic, strategy, visualize);
    }

    private void parseAndDisplayPlanResult(String planResult) {
        String[] paths = planResult.split("\n");
        HashMap<Point, PlanResult> customerBestPlan = new HashMap<>();
        deliverySearch.displayGrid();

        for (String path : paths) {
            PlanResult currentPlan = PlanResult.parse(path);
            if (customerBestPlan.containsKey(currentPlan.customerPoint)) {
                if (currentPlan.cost < customerBestPlan.get(currentPlan.customerPoint).cost) {
                    customerBestPlan.put(currentPlan.customerPoint, currentPlan);
                }
            } else {
                customerBestPlan.put(currentPlan.customerPoint, currentPlan);
            }
        }

        customerBestPlan.values().forEach(System.out::println);
    }
}
