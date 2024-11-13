package de.giuberlin;

import de.giuberlin.search.strategies.Strategies;

public class DeliverySearch extends GenericSearch implements DeliverySearchInterface {
    
    DeliverySearch(Strategies strategyCode) {
        super(strategyCode);
    }

    public static DeliverySearch usingCode(String strategyCode) {
        Strategies mappedStrategy;
        switch (strategyCode) {
            case "BF":
                mappedStrategy = Strategies.BF;
                break;
            // TODO: add rest of mappings
            default:
                mappedStrategy = Strategies.BF;
                break;
        }

        return new DeliverySearch(mappedStrategy);
    }

    @Override
    public String GenGrid() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'GenGrid'");
    }

    @Override
    public String path() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'path'");
    }

    @Override
    public String plan(String initialState, String traffic, String strategy, Boolean visualize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'plan'");
    }


}
