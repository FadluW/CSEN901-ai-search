package de.giuberlin.search.strategies;

import de.giuberlin.search.strategies.heuristics.HeuristicImpl1;

public enum Strategies {
    BF(new BreadthFirstSearch()),
    DF(new DepthFirstSearch()),
    ID(new IterativeDeepeningSearch()),
    UC(new UniformCostSearch()),
    GR1(new GreedySearch(new HeuristicImpl1())),
    GR2(new GreedySearch(new HeuristicImpl1())),
    AS1(new AStarSearch(new HeuristicImpl1())),
    AS2(new AStarSearch(new HeuristicImpl1()));

    private Strategy strategy;

    Strategies(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return this.strategy;
    }
}
