package com.jeffdalby526;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This is a fairly standard recursive backtracking algorithm used to find the path
 * between two points in a graph made up of vertices with edge nodes. By presorting
 * edge nodes "neighbor lists" by whatever weight is chosen it will find the shortest path.
 */
public class Algorithm {

    private List<Vertex> walkedPath = new ArrayList<>(); //keeps track of each step along the way
    private List<Vertex> foundPath = new ArrayList<>(); //keeps track of the successfully found path.
    private int shortestPathLength = 0; //calculates the weight of the path as we go.

    /**
     * Find shortest path between any two points. Then print out the results.
     * @pre- this will only find the shortest path if the neighbor lists of each vertix is presorted
     * @param startpoint - vertex to start the search
     * @param endpoint- vertext to end the search
     */
    public void findShortestPath(Vertex startpoint, Vertex endpoint){
        //make sure we have the start point in the path
        walkedPath.add(startpoint);
        //make sure the algorithm knows we've already considered this node
        startpoint.setVisited(true);
        findPath(startpoint,endpoint); //find the paths
        //add the start point to the found path
        foundPath.add(startpoint);
        //Items are added to the shortest path list as found so we'll need to reverse it to get the correct path
        Collections.reverse(foundPath);

        //print out results
        System.out.println("Sequence of all nodes: " + walkedPath);
        System.out.println("Shortest found path: " + foundPath);
        System.out.println("Shortest path length: " + shortestPathLength);
        System.out.println("\n");
    }

    /**
     * Recursive Implementation of the backtracking algorithm to fine the path
     * @param startpoint - vertex to start the search
     * @param endpoint - vertext to end the search
     * @return - returns false if a path is not found...in this case we are guaranteed a path
     *           and so this is only used for recursion
     */
    private boolean findPath(Vertex startpoint, Vertex endpoint){

        //base case
        if (startpoint == endpoint ) {
            return true;
        }
        //pick shortest adjacent node, which has not been visited
        //this is the top of the priority queue based on the algorithm selected
        //at the time the input file is read.  NOTE it is not based purely on the weight of the edges since
        //neither of required algorithms use purely the edge weight.

        for (Edge workingEdge:startpoint.getNeighborList()) {
            walkedPath.add(workingEdge.getVertex());  //add to the list of places we've attempted to check

            //only walk this path if we have not visited it before.
            if (!workingEdge.getVertex().isVisited())
            {

                workingEdge.getVertex().setVisited(true);
                //recursive call to find out if we can get to the end point via this vertex
                if (findPath(workingEdge.getVertex(),endpoint)) {
                    foundPath.add(workingEdge.getVertex()); // we can get there so add to the found path
                    shortestPathLength+=workingEdge.getEdgeWeight(); //add it's weight to the total length
                    return true; //break us out of the search
                }
            }
        }
        //nope, can't get to the end via this path go back.
        return false;

    }

}
