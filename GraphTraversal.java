//------------------------------------------------------------------------
// 2023 IT-ELAI Introduction to AI
// Topic : Informed Search Algorithms
//------------------------------------------------------------------------
//
// File Name    :   GraphTraversal.java
// Class Name:  :   GraphTraversal 
// Stereotype   :   
//
// GraphTraversal class:
//  Methods:
//      +addPlace                        - Add a place in string format.
//      +connect                         - Connect one vertex to another vertex.
//      +displayAdjacencyList            - Display adjacency list.
//      +breadthFirstSearch              - Traverse the map using BFS
//      +depththFirstSearch              - Traverse the map using DFS
//      +greedyBestFirstSearch           - Traverse the map using greedy best first search alg
//  Utility:
//      -getNodeByName                   - search the map using the string name
//      -reconstruct_path                - reconstruct the solution/path
//      -getLowestFScore                 - get the lowest fscore in a given list of nodes
//  Attributes:
//      -graph(LinkedList<Node>)         - Number of places/vertices in the map.

//------------------------------------------------------------------------
// Notes:
//   Comment character code - UTF-8.
//------------------------------------------------------------------------
//  Change Activities:
// tag  Reason   Ver  Rev Date       Author      Description.
//------------------------------------------------------------------------
// $000 -------  0.1  001 2023-03-25 cabrillosa  First Release.
// $001 -------  0.5  002 2023-04-29 cabrillosa  Added Greedy BFS and A*

import java.util.*;
class GraphTraversal 
{
    //---------------------------------------------------------------------
    // Attribute Definition.
    //---------------------------------------------------------------------
    private LinkedList<Node> graph;

    //------------------------------------------------------------------------
    //  Method Name : GraphTraversal
    //  Description : Constructor. Initialize the need attributes.
    //  Arguments   : void.
    //  Return      : void.
    //------------------------------------------------------------------------
    public GraphTraversal()
    {
        graph = new LinkedList<Node>();
    }

    //------------------------------------------------------------------------
    //  Method Name : addPlace
    //  Description : Adds a place in string format.
    //  Arguments   : String place
    //                float h
    //  Return      : void
    //------------------------------------------------------------------------
    public void addPlace(String name, float h) {
        Node newnode = new Node(name.toLowerCase());
        newnode.h = h;
        graph.add(newnode);
    }
    
    //------------------------------------------------------------------------
    //  Method Name : connect
    //  Description : Connect one vertex to another vertex.
    //  Arguments   : string v1
    //                string v2
    //                float distance
    //  Return      : 0 (OK)
    //               -1 (NG - place is not in the list)
    //------------------------------------------------------------------------

    public int connect(String place1, String place2, float distance) 
    {
        Node p1 = getNodeByName(place1.toLowerCase());
        Node p2 = getNodeByName(place2.toLowerCase());

        if(p1 == null || p2 == null) {
            System.out.println("Error: Could'nt find the places!");
            return -1;
        } else { /* nothing todo */ }

        p1.addNeighbor(p2, distance);
        p2.addNeighbor(p1, distance);

        return 0;
    }

    //------------------------------------------------------------------------
    //  Method Name : displayAdjacencyList
    //  Description : Display adjacency list.
    //  Arguments   : None.
    //  Return      : None.
    //------------------------------------------------------------------------
    public void displayAdjacencyList()
    {
        Iterator<Node> node_ite = graph.iterator();

        while(node_ite.hasNext()) {
            Node n = node_ite.next(); //temp = temp.next
            Iterator<Neighbor> neighbor_ite = n.neighbors.iterator();

            System.out.print(n.name +"::>");
            while(neighbor_ite.hasNext()) {
                Neighbor neighbor = neighbor_ite.next();
                System.out.print(neighbor.node.name + "->");
            }
            System.out.println();//print newline
        }
    }

    //------------------------------------------------------------------------
    //  Method Name : breadthFirstSearch
    //  Description : Traverse the map using BFS
    //  Arguments   : String s
    //                String g
    //  Return      : Void
    //------------------------------------------------------------------------
    public void breadthFirstSearch(String s, String g)
    {
        Queue<Node> q = new LinkedList<Node>();
        Node start_node = getNodeByName(s.toLowerCase());
        if(start_node == null) {
            System.out.println("Error: unable to find the string place!");
            return;
        } else {/*nothing todo */}

        q.add(start_node);
        
        start_node.isVisited = true;
        
        while(q.size() > 0) 
        {
            Node v = q.remove();

            if(v.name.equals(g.toLowerCase())) {
                reconstruct_path(v);
                System.out.println("Found!");
                unvisit();
                return;
            }

            System.out.print(v.name + "->");
            Iterator<Neighbor> neighbor_ite = v.neighbors.iterator();

            while(neighbor_ite.hasNext()){
                Neighbor neighbor = neighbor_ite.next();
                if( neighbor.node.isVisited != true)
                {
                    q.add(neighbor.node);
                    neighbor.node.parent = v;
                    neighbor.node.isVisited = true;
                }
            }
        }
        unvisit();
        System.out.println();
        System.out.println("No solution!");
    }

    //------------------------------------------------------------------------
    //  Method Name : depththFirstSearch
    //  Description : Traverse the map using BFS
    //  Arguments   : String s
    //                String g
    //  Return      : Void
    //------------------------------------------------------------------------
    public void depthFirstSearch(String s, String g)
    {
        Stack<Node> st = new Stack<Node>();
        Node start_node = getNodeByName(s.toLowerCase());
        if(start_node == null) {
            System.out.println("Error: unable to find the string place!");
            return;
        } else {/*nothing todo */}

        st.push(start_node);
        
        start_node.isVisited = true;
        
        while(st.size() > 0) 
        {
            Node v = st.pop();

            if(v.name.equals(g.toLowerCase())) {
                reconstruct_path(v);
                System.out.println("Found!");
                unvisit();
                return;
            }
            System.out.print(v.name + "->");
            Iterator<Neighbor> neighbor_ite = v.neighbors.iterator();

            while(neighbor_ite.hasNext()){
                Neighbor neighbor = neighbor_ite.next();
                if( neighbor.node.isVisited != true)
                {
                    st.push(neighbor.node);
                    neighbor.node.parent = v;
                    neighbor.node.isVisited = true;
                }
            }
        }
        unvisit();
        System.out.println();
        System.out.println("No solution!");

    }

    //------------------------------------------------------------------------
    //  Method Name : depththFirstSearch
    //  Description : Traverse the map using BFS
    //  Arguments   : String s
    //                String g
    //  Return      : Void
    //------------------------------------------------------------------------
    public void greedyBestFirstSearch(String start_place, String goal_place)
    {
        Node start = getNodeByName(start_place);
        PriorityQueue<Node> pq = new PriorityQueue<Node>();

        if (start == null) {
            System.out.println("Enter a valid start node!");
        } else {}

        start.f = start.h;
        start.isVisited = true;
        pq.add(start);

        while(pq.size() > 0) {
            Node current = pq.poll();
            current.isVisited = true;

            if(current.name == goal_place.toLowerCase()) {
                reconstruct_path(current);
                unvisit();
                return;
            }

            Iterator<Neighbor> neighbor_ite = current.neighbors.iterator();
            while(neighbor_ite.hasNext()) {
                Neighbor n = neighbor_ite.next();
                
                if(n.node.isVisited != true) {
                    n.node.parent = current;
                    n.node.f = n.node.h;
                    pq.add(n.node);
                }
            }
        }
        System.out.println("No solution!");
        unvisit();
    }

    //------------------------------------------------------------------------
    //  Method Name : aStar
    //  Description : Traverse the map using aStar
    //  Arguments   : String s
    //                String g
    //  Return      : Void
    //------------------------------------------------------------------------
    public void aStar(String start_place, String goal_place)
    {
        Node start = getNodeByName(start_place);

        LinkedList<Node> openlist = new LinkedList<Node>();
        LinkedList<Node> closedlist = new LinkedList<Node>();

        start.f = start.g + start.h;
        openlist.add(start);

        while(openlist.size() > 0) {
            Node current = getLowestFScore(openlist);

            if(current.name == goal_place.toLowerCase()) {
                //solution found
                System.out.println("Final fscore = "+ current.f);
                reconstruct_path(current);
                return;
            } else {}

            Iterator<Neighbor> neighbor_ite = current.neighbors.iterator();

            while(neighbor_ite.hasNext()) {
                Neighbor m = neighbor_ite.next();
                float gtotal = current.g + m.distance;

                if(!closedlist.contains(m.node) && !openlist.contains(m.node)) {
                    m.node.parent = current;
                    m.node.g = gtotal;
                    m.node.f = m.node.g + m.node.h;
                    openlist.add(m.node);
                } else {
                    if(gtotal < m.node.g) {
                        m.node.parent = current;
                        m.node.g = gtotal;
                        m.node.f = m.node.g + m.node.h;

                        if (closedlist.contains(m.node)){
                            openlist.add(m.node);
                        }
                    }
                }
            }
            openlist.remove(current);
            closedlist.add(current);
        }
        System.out.println("No path to goal!");
    }

    
    //UTILITY FUNCTIONS

    //------------------------------------------------------------------------
    //  Method Name : getNodeByName
    //  Description : get the node by its name
    //  Arguments   : String name
    //  Return      : Node, if name is found
    //                null, if name is not found
    //------------------------------------------------------------------------
    public Node getNodeByName(String name) {
        

        Iterator<Node> node_ite = graph.iterator();

        while(node_ite.hasNext()) { // while(temp != null)
            Node n = node_ite.next(); // temp = temp.next
            
            //found
            if(n.name.equals(name)) {
                return n;
            }
        }

        return null;
    }

    //------------------------------------------------------------------------
    //  Method Name : unvisit
    //  Description : reconstruct the path from goal to start
    //  Arguments   : void
    //  Return      : Node, if name is found
    //                null, if name is not found
    //------------------------------------------------------------------------
    private void unvisit(){
        Iterator<Node> node_ite = graph.iterator();

        while(node_ite.hasNext()) {
            Node n = node_ite.next();
            n.isVisited = false;
        }
    }

    //------------------------------------------------------------------------
    //  Method Name : reconstruct_path
    //  Description : reconstruct the path from goal to start
    //  Arguments   : String lastnode
    //  Return      : Node, if name is found
    //                null, if name is not found
    //------------------------------------------------------------------------
    private void reconstruct_path(Node lastnode)
    {
        System.out.println("Reconstructing path.");
        LinkedList<Node> path = new LinkedList<Node>();
        while(lastnode != null) {
            path.addFirst(lastnode);
            lastnode = lastnode.parent; // move backward
        }
        
        Iterator<Node> node_ite = path.iterator();
        while(node_ite.hasNext()) {
            Node temp = node_ite.next();
            System.out.print(temp.name + "->");
        }

        System.out.println();
    }

    public Node getLowestFScore(LinkedList<Node> list) {
        Iterator<Node> node_ite = list.iterator();
        
        node_ite.hasNext();
        Node lowest = node_ite.next();

        while(node_ite.hasNext()) {
            Node temp = node_ite.next();
            if(lowest.f > temp.f) {
                lowest = temp;
            }
        }

        return lowest;
    }
}