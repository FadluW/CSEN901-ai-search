package de.giuberlin;

import de.giuberlin.grid.types.Customer;
import de.giuberlin.grid.types.Store;
import de.giuberlin.search.NodePath;

public class DeliverySearchPlanOutput {
    private final Customer customer;
    private final Store store;
    private final NodePath path;

    public DeliverySearchPlanOutput(Customer customer, Store store, NodePath path) {
        this.customer = customer;
        this.store = store;
        this.path = path;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Store getStore() {
        return store;
    }

    public NodePath getPath() {
        return path;
    }
}
