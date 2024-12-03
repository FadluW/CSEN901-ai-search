package de.giuberlin.search.strategies.heuristics;

import de.giuberlin.search.SearchNode;
import grid.GridObject;
public class Heuristic {
    private int HeuristicVal1;
    private int HeuristicVal2;


    public Heuristic(int HeuristicVal1, int HeuristicVal2, SearchNode node,SearchNode goal ){


        HeuristicVal1=(int)node.getPath().getCost() +(goal.getGridObject()).eulclideanDistance(node.getGridObject());
        HeuristicVal2=(int)node.getPath().getCost() +(goal.getGridObject()).manhattanDistanceTo(node.getGridObject());
    

    }

    
    public int getHeuristicVal1(){
        return HeuristicVal1;
    }


    public int getHeuristicVal2(){
        return HeuristicVal2;
    }


   // public void calculate (SearchNode n1, SearchNode g); //coordinates of node,coordinates of goal.



}
