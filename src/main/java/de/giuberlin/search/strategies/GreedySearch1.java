package de.giuberlin.search.strategies;

import java.util.PriorityQueue;

import de.giuberlin.search.SearchNode;
import de.giuberlin.search.strategies.heuristics.Heuristic;

import de.giuberlin.search.strategies.comparators.SearchNodeGreedyComparator1;


public class GreedySearch1 implements Strategy {
    PriorityQueue<SearchNode> queue = new PriorityQueue<>(new SearchNodeGreedyComparator1());
    // private Heuristic h;
    
    // GreedySearch(Heuristic h) {
    //     this.h = h;
    // }
    
    @Override
    public void enqueue(SearchNode node) {
        // TODO Auto-generated method stub
        queue.add(node);
        
    }

    @Override
    public SearchNode dequeue() {
        // TODO Auto-generated method stub
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
