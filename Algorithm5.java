/*
Use of Data Structures:

    There are 2 ways to represent a Graph, either as an adjacency list or a matrix. 
    Because we do not know whether the Graph is dense, it makes sense to use an
    adjacency list. The complexity for the cases when the graph is sparse, 
    and have equal performance when the graph is dense.
    To auxiliate the implementation a Priority Queue was used.
    To represent the adjacency list the ArrayList of java was chosen, because it uses
    a sequential allocation of memory, allowing for the O(n) = 1 accessing of elements.
    In order to avoid the reallocation of memory(which could cause the appending on 
    the list to become O(n) = n), it was used the ensureCapacity function with the 
    maximum size that the list could possibly contain. The use of this function 
    dramatically decreases the complexity in the worst case scenario.


Asymptotic Analysis:

    Defining E = number of edges and V = number of Vertices.
    For the Dijkstra algorithm: O (V + E * log V)

*/
import java.util.Locale;
import java.util.ArrayList;
import java.io.FileReader; 
import java.util.Scanner;
import java.lang.reflect.Method;
import java.lang.Double;
import java.lang.Integer;
import java.lang.Float;
import java.util.function.*;
import java.util.*;
import java.util.LinkedList;


class Edge implements Comparable<Edge>{
    int vertex;
    float cost;

    Edge( int vertex, float cost ){
        this.vertex = vertex;
        this.cost = cost;
    }

    void addToCost( float newCost ){
        this.cost = this.cost + newCost;
    }

    //This comparator is designed to make the priority queue sort on ascending
    //order.
    @Override
    public int compareTo( Edge second ){
        if( this.cost > second.cost )
            return 1;
        else
            return -1;
    }
}


class Graph{
    boolean visited[];
    boolean isRecheable;
    final int targetVertex;
    final int initialVertex;
    final int numberOfVertices;
    final int numberOfEdges;
    int[] previous;
    float time;
    float[] accumulatedCosts;
    PriorityQueue< Edge > lowestValueEdge;
    LinkedList< Integer > path;
    ArrayList< ArrayList< Edge >> adjacencyList;

    Graph( int numberOfVertices, int numberOfEdges ){
        this.targetVertex       = numberOfVertices-1;
        this.initialVertex      = 0;
        this.numberOfVertices   = numberOfVertices;
        this.numberOfEdges      = numberOfEdges;
        this.previous           = new int[ numberOfVertices ];
        this.lowestValueEdge    = new PriorityQueue< Edge >();
        this.accumulatedCosts   = new float[this.numberOfVertices];
        this.visited            = new boolean[ numberOfVertices ];
        this.path               = new LinkedList<Integer>();
        this.adjacencyList      = new ArrayList< ArrayList< Edge > >( );
        this.adjacencyList.ensureCapacity( numberOfVertices );
        for( int i=0; i<numberOfVertices; i++ ){
            this.adjacencyList.add( new ArrayList< Edge >() );
            this.adjacencyList.get(i).ensureCapacity( numberOfVertices );
        }
    }

    //this function inserts a new edge on the representation of the graph
    void addEdge( int vertexId, int otherVertex, float cost ){
        adjacencyList.get(vertexId).add( new Edge( otherVertex, cost ) );
    }

    //this function will get the waiting time from the external function that should be passed
    static float getWaitingTime( int i, float time ){
        //change here to get the real waiting time function.
        return ExampleWaitingFunction.waitingTime( i, time );
    }

    //Implementation of Djakstra`s algorithm.
    void findMinimalPath(){
        this.lowestValueEdge.add( new Edge( this.initialVertex, 0 ) );

        //we will start on one because the initial vertex is 0, and therefore 
        //the distance of the 0 vertex is 0.
        for( int i=1; i<this.numberOfVertices; i++ )
            accumulatedCosts[i] = Float.MAX_VALUE;

        while( this.lowestValueEdge.isEmpty() == false ){
            int currentVertex = lowestValueEdge.poll().vertex;
            this.visited[ currentVertex ] = true;
            float wait = getWaitingTime( currentVertex, accumulatedCosts[ currentVertex ] );

            for( Edge edge : this.adjacencyList.get( currentVertex ) ){
                int nextVertex = edge.vertex;
                if( visited[ nextVertex ]  )  continue;
                edge.addToCost( wait );
                this.lowestValueEdge.add( edge );
                float tempDistance = accumulatedCosts[ currentVertex ] + edge.cost;
                if( tempDistance < accumulatedCosts[ nextVertex ] ){
                    accumulatedCosts[ nextVertex ] = tempDistance;
                    previous[ nextVertex ] = currentVertex;
                }
            }
        }
        this.time = this.accumulatedCosts[ this.targetVertex ];
    }

    //this function serves to define if the target version is reacheable or not.
    void isRecheable(){
        if( this.accumulatedCosts[ this.targetVertex ] == Float.MAX_VALUE )
            this.isRecheable = false;
        else
            this.isRecheable = true;
    }

    //O(v) = v
    //because the djikstra`s algorithm saves the path from last to first and it
    //is required to print it form first to last, we reverse it to get the path on
    //the right order. because it is only iterated on one order (no access to inermediate
    //elements), in order to save on the performance, a linked structure is better suited,
    //such as linked list or a stack. 
    void reconstructPath( ){
        int currentVertex = this.targetVertex;
        while( currentVertex != this.initialVertex ){
            this.path.addFirst(currentVertex);
            currentVertex = this.previous[ currentVertex ];
        }
        this.path.addFirst(currentVertex);
    }

    void solve(){
        this.findMinimalPath();
        this.isRecheable();
        this.reconstructPath( );
    }

    //prints the final solution as it is described on the problem description
    void displayAnswer(){
        if( this.isRecheable == false ){
            System.out.println("unrecheable");
            return;
        }
        System.out.println( this.time );
        for( int vertex : this.path )
            System.out.printf( "%d ", vertex );
        System.out.println();
    }
}

//Placeholder for the waiting function that will be given.
class ExampleWaitingFunction{
    static float waitingTime( int i, float time ){
        return (float) 5.0;
    }
}

class Algorithm5{
    public static void main( String args[] ) throws Exception {
        Locale.setDefault(Locale.US);
        int numberOfVertices, numberOfEdges;
        int firstVertex, secondVertex;
        float cost;
        FileReader reader = new FileReader( args[0] );
        Scanner scan = new Scanner( reader );
        numberOfVertices = scan.nextInt();
        scan.nextLine();
        numberOfEdges = scan.nextInt();
        scan.nextLine();
        Graph graph = new Graph( numberOfVertices, numberOfEdges );
        for( int i=0; i<numberOfEdges; i++ ){
            firstVertex = scan.nextInt();
            secondVertex = scan.nextInt();
            cost = scan.nextFloat();
            scan.nextLine();
            graph.addEdge( firstVertex, secondVertex, cost );
        }
        graph.solve();
        graph.displayAnswer();
    }
}
