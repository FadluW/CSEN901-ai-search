package de.giuberlin.search.strategies;

import de.giuberlin.search.strategies.heuristics.HeuristicImpl1;

public enum StrategyCode {
    BF(new BreadthFirstSearch()),
    DF(new DepthFirstSearch()),
    ID(new IterativeDeepeningSearch()),
    UC(new UniformCostSearch()),
    GR1(new GreedySearch1()),
    GR2(new GreedySearch2()),
    AS1(new AStarSearch1()),
    AS2(new AStarSearch2());


    private Strategy strategy;

    StrategyCode(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return this.strategy;
    }
}
