package de.giuberlin.search.strategies;

import java.util.PriorityQueue;

import de.giuberlin.search.SearchNode;
import de.giuberlin.search.strategies.comparators.SearchNodeAStarComparator1;
import de.giuberlin.search.strategies.comparators.SearchNodeAStarComparator2;
import de.giuberlin.search.strategies.heuristics.Heuristic;
import de.giuberlin.search.strategies.heuristics.HeuristicImpl1;

public class AStarSearch2 implements Strategy {
   // private HeuristicImpl1 h;
    PriorityQueue<SearchNode> queue = new PriorityQueue<>(new SearchNodeAStarComparator2());

    
    // AStarSearch1(HeuristicImpl1 h) {
    //     this.h = h;
    // }

    @Override
    public void enqueue(SearchNode node) {
      
        queue.add(node);
    }

    @Override
    public SearchNode dequeue() {
        return queue.poll();
    }

    @Override
    public void displayQueue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayQueue'");
    }

    @Override
    public boolean isInformed() {
        return true;
    }
}
