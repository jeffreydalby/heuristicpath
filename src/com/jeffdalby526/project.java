package com.jeffdalby526;

import java.util.List;
import java.util.Scanner;


/**
 * Application that implements to heuristic algorithms to find a shortest path between any node and node Z
 * reads a distance file and a graph to create an edgelist, which then allows user to select the starting node
 * and prints out the shortest path found using two seperate weights
 *
 */
public class project {

    //FILE NAMES TO CHANGE FOR TESTING
    String distanceFile = "direct_distance_3.txt";
    String graphFile = "graph_input_3.txt";

    String selectionList = "";  //simple string to keep track of all possible nodes
    List<Vertex> algoritm1EdgeList;  //edge list using algorithm 1
    List<Vertex> algoritm2EdgeList;  //edge list using algorithm 2


    /**
     *  Main Method
     * @param args - unused
     */
    public static void main(String[] args) {

        project myProject = new project();
        //Read data into our edge lists
        myProject.readData();
        //Create our selection list
        myProject.buildSelectionList();
        //display the menu
        myProject.showMenu();
    }

    /**
     * Console menu loop allowing selection of an edge
     */
    private void showMenu(){
        String selection = "";
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            System.out.println("Please select a start point from the following (QQ to quit): ");
            //Display possible selections just to make things easier for the user.
            for (int i=0;i < selectionList.length() -1 ;i++ )
                {
                    System.out.print(Character.toUpperCase(selectionList.charAt(i)) + ",");
                }
            //add the last item without the comma
            System.out.println(Character.toUpperCase(selectionList.charAt(selectionList.length()-1)));
            selection = sc.nextLine(); //read the selection
            if (selection.toLowerCase().equals("qq") ) break;  //exit if they want to quit
            //make sure it is a valid selection, find and output the paths
            if (selectionList.contains(selection.toLowerCase())) {
                findPaths(selection.toLowerCase());
                //reset visited to true on all nodes so we can do the search again without having to re-read or recreate the objects
                reset();
            }
            //let user know they choose poorly.
            else
                System.out.println("Please select a start point that is in the list.");
        }

    }

    /**
     * Read in the data files and create the two lists (one for each algorithm)
     */
    private void readData(){
        GraphReader algorithm1Reader = new GraphReader<Edge>();
        algoritm1EdgeList = algorithm1Reader.createEdgeList(distanceFile,graphFile, Edge.class);
        GraphReader algorithm2Reader = new GraphReader<AltWeightedEdge>();
        algoritm2EdgeList = algorithm2Reader.createEdgeList(distanceFile,graphFile,AltWeightedEdge.class);
    }

    /**
     * Find the paths using each algorithm based on user selected vertex
     * Note this actually can be used to find the path to whatever item is last in the list
     * In this case that will always be Z based on the requirements doc but this allows for more flexibility.
     * @param selection- node selected to begin the search
     */
    private void findPaths(String selection){
        //Create an instance of algorithm to handle each case.
        Algorithm alg1 = new Algorithm();
        Algorithm alg2 = new Algorithm();

        System.out.println("Using Algorithm 1 from " + selection.toUpperCase() + " to " + algoritm1EdgeList.get(algoritm1EdgeList.size()-1).getData() );
        //find the path using algorithm1 passing the starting vertex and ending vertex from the edge list
        alg1.findShortestPath(algoritm1EdgeList.get(selectionList.indexOf(selection)),algoritm1EdgeList.get(algoritm1EdgeList.size()-1));

        System.out.println("Using Algorithm 2 from " + selection.toUpperCase() +  " to " + algoritm1EdgeList.get(algoritm1EdgeList.size()-1).getData() );
        //find the path using algorithm2 passing the starting vertex and ending vertex from the edge list
        alg2.findShortestPath(algoritm2EdgeList.get(selectionList.indexOf(selection)),algoritm2EdgeList.get(algoritm2EdgeList.size()-1));
    }

    /**
     * Reset Edge Lists by looping through and setting visited to false.
     */
    private void reset(){
        for (Vertex v:algoritm1EdgeList
             ) {
            v.setVisited(false); }
        for (Vertex v:algoritm2EdgeList
                ) {
            v.setVisited(false); }

    }

    /**
     * Create a selection list by looping through the edge list and grabbing the name
     * of each vertex.
     */
    private void buildSelectionList(){
        for (Vertex v:algoritm1EdgeList){
            selectionList+=v.getData().toLowerCase();
        }
    }


}
