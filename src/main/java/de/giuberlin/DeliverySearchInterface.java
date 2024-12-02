package de.giuberlin;

import de.giuberlin.search.strategies.Strategy;
import de.giuberlin.grid.types.GridObject;

public interface DeliverySearchInterface {
    /**
     * Generates a random grid with at least one customer and one store.
     * Where: 1 <= P <= 10 and 1 <= S <= 3
     * 
     * 
     * @return String representation of the grid (;) separated.
     */
    public String GenGrid();

    /**
     * Uses the given search strategy to find the path for a truck to reach a given destination. 
     * See also {@link de.giuberlin.search.NodePath NodePath}.
     * 
     * @return String with the following format plan;cost;nodesExpanded
     */
    public String path(Strategy searchStrategy, GridObject start, GridObject goal);
    
    
    /**
     * Finds the final plan including which truck will deliver which product.
     * 
     * @param initialState String representation of how the grid looks like initially
     * @param traffic String representation of the traffic between segments in the city 
     * @param strategy Search strategy to be applied
     * @param visualize Boolean to toggle displaying pathing in the console
     * @return String of pairs for each store and destination
     */
    public String plan(
        String initialState, 
        String traffic, 
        String strategy, 
        Boolean visualize);
}
