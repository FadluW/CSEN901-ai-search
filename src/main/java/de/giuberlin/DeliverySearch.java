package de.giuberlin;

import de.giuberlin.search.strategies.Strategy;
import de.giuberlin.search.strategies.StrategyCode;
import grid.GridObject;

public class DeliverySearch extends GenericSearch implements DeliverySearchInterface {

    @Override
    public String GenGrid() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'GenGrid'");
    }

    @Override
    public String path(Strategy searchStrategy, GridObject start, GridObject goal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'path'");
    }

    @Override
    public String plan(String initialState, String traffic, String strategy, Boolean visualize) {
        // Create grid to use

        // Add traffic between each nodes

        // Map search strategy
        Strategy searchStrategy = StrategyCode.valueOf(strategy.toUpperCase()).getStrategy();

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'plan'");
    }


}
