package com.jeffdalby526;


/**
 * Modifies the weighting of an edge to match the requirement:
 * "Among all nodes v that are adjacent to the node n, choose the one for which w(n, v) + dd(v) is the smallest."
 */
public class AltWeightedEdge extends Edge {

    // comparision used by priority queue to sort vertices directly as they are added to the neighbor lists.
    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.getVertex().directDistance() + this.getEdgeWeight(), other.getVertex().directDistance() + other.getEdgeWeight());
    }
}
