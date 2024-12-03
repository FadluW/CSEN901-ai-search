package de.giuberlin.search.strategies.comparators;
import java.util.Comparator;
import de.giuberlin.search.strategies.heuristics.Heuristic;
//import de.giuberlin.search.strategies.heuristics.HeuristicImpl1;

import de.giuberlin.search.SearchNode;
public class SearchNodeAStarComparator2 implements Comparator<SearchNode>  {
    SearchNode goal;
    SearchNode node1;
    SearchNode node2;

     Heuristic node1H =new Heuristic(0,0,node1,goal);
     Heuristic node2H =new Heuristic(0,0,node2,goal);


     //HeuristicImpl1 h2; 


    @Override
    public int compare(SearchNode node1, SearchNode node2) { //comprae between h1 of node 1 and 2
      int costCompare = Long.compare(node1.getPath().getCost()+ node1H.getHeuristicVal2(), node2.getPath().getCost()+ node2H.getHeuristicVal2());
         if (costCompare != 0) {
            return costCompare;
         }
        return Integer.compare(node1.getOrderDiscovered(), node2.getOrderDiscovered());
    }



}
