package de.giuberlin.search.strategies.comparators;

import java.util.Comparator;

import de.giuberlin.search.SearchNode;

public class SearchNodeUniformCostComparator implements Comparator<SearchNode> {

    @Override
    public int compare(SearchNode node1, SearchNode node2) {
        int costCompare = Integer.compare(node1.getCostToExpand(), node2.getCostToExpand());
        if (costCompare != 0) {
            return costCompare;
        }
        return Integer.compare(node1.getOrderDiscovered(), node2.getOrderDiscovered());
    }
}