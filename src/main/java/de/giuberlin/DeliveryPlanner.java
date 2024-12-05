package de.giuberlin;

public class DeliveryPlanner {
    private DeliverySearch deliverySearch;
    private String initialState;
    private String traffic;
    private String strategy;
    private boolean visualize;

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
}
