package com.jeffdalby526;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class to create edge lists from a pair of graph input files.
 * First we read the Direct Distance file, which allows creation of an Edge List (implemented as an array list)
 * It is required that this distance file matches the order and number of nodes created in the graph file itself.
 * The class utilizes generics to allow for different weighting of the edges based on different algorithms in our requirements.
 * By passing the correct Edge type we can ensure neighbor lists are created in the proper order.
 * @param <E> - Type of edge class to be used for calculating "real" edge weights.
 */

public class GraphReader<E extends Edge> {

    private List<Vertex> vertices = new ArrayList<>(); //our edge list to return


    /**
     * Create the edgelist from a direct distance file and a graph file.
     * @param directDistanceFile - filename for dd file
     * @param graphInputFile- file name of correcsponding graph
     * @param edgeClass- passed in so we can identify the type of edge needed
     * @return- the created edge list.
     */
    public List<Vertex> createEdgeList(String directDistanceFile, String graphInputFile, Class<E> edgeClass){

        readDistanceFile(directDistanceFile);
        readGraphFile(graphInputFile, edgeClass);
        return vertices;

    }

    /**
     * Read the graph file and populate the neighborlists for the existing vertices.
     * @param graphInputFile- name of graph file
     * @pre DistanceFile must have been successfully read in to create the vertices list!!
     */

    private void readGraphFile(String graphInputFile, Class<E> edgeClass ) {
        try{

            File distanceFile = new File(graphInputFile);
            Scanner sc = new Scanner(distanceFile);
            //skip the first line of the file which contains labels
            sc.nextLine();

            //each line (after the first) corresponds directly to each vertix in the pre-populated list
            //so we can iterate through those vertices to find the neighbors from the graph file
            for (Vertex vertex:vertices
                 ) {
                String[] line = sc.nextLine().split("\\s+");  // parse our string to find out edge weights
                //loop through the line to find columns where the edge weight was greater than 0
                //each item represents a vertex in our existing edge list and so we can just add the neighbor to that vertex location
                //starting at 1 to ignore the label contained in the file
                for (int vertexLocation = 1; vertexLocation < line.length; vertexLocation++){
                    //found an edge
                        if (Integer.parseInt(line[vertexLocation]) > 0)
                        {
                            //make a new edge (needed the class type in order to handle the generic)
                            E theEdge = edgeClass.getConstructor().newInstance();
                            //point the edge to the proper existing vertex
                            theEdge.setVertex(vertices.get(vertexLocation-1));
                            //set the edge weight of this edge
                            theEdge.setEdgeWeight(Integer.parseInt(line[vertexLocation]));
                            //add to the neightborlist priority queue automatically sorting it by the correct algorithm.
                            vertex.addNeighborVertex(theEdge);
                        }

                }

            }

        }
        //Catch that we couldn't read the file, print out the stack and exit the JVM.
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Failed to read " + graphInputFile);
            System.exit(0);
        }


    }

    /**
     * Read the direct distance file and create the initial edge list
     * @param directDistanceFile- name of the file to read
     * @post- edge list has been populated with vertices with direct distance assigned
     *
     */

    private void readDistanceFile(String directDistanceFile) {

        try {
            File distanceFile = new File(directDistanceFile);
            Scanner sc = new Scanner(distanceFile);

            //read each line parse out the direct distance, create new vertex and add to list
            while (sc.hasNext()) {
                String[] line = sc.nextLine().split("\\s+");
                Vertex theVertex = new Vertex(line[0],Integer.parseInt(line[1]));
                vertices.add(theVertex);
            }
            sc.close();
        }
        //Catch that we couldn't read the file, print out the stack and exit the JVM.
        catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Failed to read " + directDistanceFile);
            System.exit(0);
        }


    }

}
