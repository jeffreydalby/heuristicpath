package com.jeffdalby526;

/**
 * The Edge Class provides a method to store all of the edges of a vertex in its neighbor list
 * Each edge contains a vertex and it's edge weight.  It implements Comparable, which allows us to be
 * "automatically" sorted when adding to a priority queue.
 */

public class Edge implements Comparable<Edge> {

    //Standard getters and setters for vertex and edge
    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    public int getEdgeWeight() {
        return edgeWeight;
    }

    public void setEdgeWeight(int edgeWeight) {
        this.edgeWeight = edgeWeight;
    }

    private Vertex vertex;  //the vertex the neighbor is pointing to
    private int edgeWeight; //the weight of the edge

    /**
     * default constructor build with an empty vertex and 0 weight.
     */
    public Edge(){
        this(new Vertex(),0);
    }

    /**
     * Constructor to create the edge pointing to the appropriate node with the given weight
     * @param vertex- the vertex this edge points to
     * @param edgeWeight- weight of the edge
     */

    public Edge(Vertex vertex, int edgeWeight){
        this.vertex = vertex;
        this.edgeWeight = edgeWeight;
    }

    /**
     * Comparison to insert the edge into a priority queue meeting the requirement:
     * "Among all nodes v that are adjacent to the node n, choose the one with the smallest dd(v)."
     * NOTE: This is not using the edge weight! Per the algorithm this uses the direct distance which is stored on each vertex.
     * @param other - edge to be compared to
     * @return-  the value 0 if x == y; a value less than 0 if x < y; and a value greater than 0 if x > y
     */
    @Override
    public int compareTo(Edge other) {
        int returnVal = Integer.compare(this.getVertex().directDistance(), other.getVertex().directDistance());
        return returnVal;
    }
}
