package de.giuberlin;

import de.giuberlin.grid.Grid;
import de.giuberlin.grid.types.GridObject;
import de.giuberlin.search.strategies.Strategy;
import de.giuberlin.search.strategies.StrategyCode;
import java.awt.*;

public class DeliverySearch extends GenericSearch implements DeliverySearchInterface {

    Grid grid;
    @Override
    public String GenGrid() {

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

        addTraffic(traffic);

        // Map search strategy
        Strategy searchStrategy = StrategyCode.valueOf(strategy.toUpperCase()).getStrategy();

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'plan'");
    }

    private void addTraffic(String traffic){
        String[] segments = traffic.split(";");
        for (String segment : segments) {
            String[] segmentValues = segment.split(",");
            int trafficValue = Integer.parseInt(segmentValues[4]);
            if (trafficValue < 1)
                continue;

            Point source = new Point(Integer.parseInt(segmentValues[0]), Integer.parseInt(segmentValues[1]));
            Point destination = new Point(Integer.parseInt(segmentValues[2]), Integer.parseInt(segmentValues[3]));
            grid.setTraffic(source, destination, trafficValue);
        }
    }


}
