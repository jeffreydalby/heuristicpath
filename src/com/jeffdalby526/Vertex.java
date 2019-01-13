package com.jeffdalby526;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *   Vertex class to store vertices, neighbor lists, direct distance value and visited state
 *   Utilizes a priority queue as a method of automatically sorting edges (underlying java structure is a min-heap so it is fast)
 */

public class Vertex {

    private String data; //name of the vertex
    private boolean visited; //keep track of is this node has been visited during a path search.
    private PriorityQueue<Edge> neighborList; //auto sorted priority queue ensuring the paths chosen are based on the order of the algorithm
    private int directDistance; //direct distance used in calculating edge weights.

    //standard getters and setters

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public PriorityQueue<Edge> getNeighborList() {
        return neighborList;
    }

    public void setNeighborList(PriorityQueue<Edge> neighborList) {
        this.neighborList = neighborList;
    }

    public int directDistance() {
        return directDistance;
    }

    public void setDirectDistance(int directDistance) {
        this.directDistance = directDistance;
    }

    public int getDirectDistance() {
        return directDistance;
    }


    public Vertex(){
        this("");
    }

    public Vertex(String data){
        this(data, 1);

    }

    /**
     * Primary constructor used by this application
     * @param data - name of the Vertex
     * @param directDistance- direct distance (provided by direct_distance file)
     */
    public Vertex(String data, int directDistance)
    {
        this.data = data;
        this.neighborList = new PriorityQueue();
        this.directDistance = directDistance;
    }

    //allows us to add Edges to the Neighbor list priority queue
    public void addNeighborVertex(Edge neighbor){

        this.neighborList.add(neighbor);

    }
    //Make it easy to print out the list of vertices visited
    @Override
    public String toString(){
        return ""+ this.data;
    }

}
