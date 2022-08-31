//Use of algorithm:
//
//
//
//Analyses of complexity:
//
//
//
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
    //int secondVertex;
    float cost;

    Edge( int vertex, float cost ){
        this.vertex = vertex;
        //this.secondVertex = secondVertex;
        this.cost = cost;
    }

    @Override
    public int compareTo( Edge second ){
        if( this.cost > second.cost )
            return -1;
        else
            return 1;
    }
}


//There are 2 ways to represent a Graph, eighter as adjacency list or a matrix.
//Because we do not know wether the Graph is dense,
//it makes sense to use an adjacency list, to decrease time and space,
//complexity for the cases when the graph is sparse, and have equal performance
//when the graph is dense. 
class Graph{
    boolean visited[];
    final int targetVertex;
    final int initialVertex;
    final int numberOfVertices;
    final int numberOfEdges;
    int[] previous;
    float time;
    float[] distances;
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
        this.distances          = new float[this.numberOfVertices];
        this.visited            = new boolean[ numberOfVertices ];
        this.path               = new LinkedList<Integer>();
        this.adjacencyList      = new ArrayList< ArrayList< Edge > >( );
        this.adjacencyList.ensureCapacity( numberOfVertices );

        for( int i=0; i<numberOfVertices; i++ ){
            this.adjacencyList.add( new ArrayList< Edge >() );
            this.adjacencyList.get(i).ensureCapacity( numberOfVertices );
        }
    }

    public void addEdge( int vertexId, int otherVertex, float cost ){
        adjacencyList.get(vertexId).add( new Edge( otherVertex, cost ) );
    }

    static float getWaitingTime( int i, float time ){
        return ExampleWaitingFunction.waitingTime( i, time );
    }

    //Implementation of Djakstra`s algorithm.
    private void findMinimalPath(){
        this.lowestValueEdge.add( new Edge( this.initialVertex, 0 ) );
        //we will start on one because the initial vertex is 0, and therefore 
        //the distance of the 0 vertex is 0.
        for( int i=1; i<this.numberOfVertices; i++ )
            distances[i] = Float.MAX_VALUE;

        while( this.lowestValueEdge.isEmpty() == false ){
            int currentVertex = lowestValueEdge.poll().vertex;
            this.visited[ currentVertex ] = true;
            for( Edge edge : this.adjacencyList.get( currentVertex ) ){
                int nextVertex = edge.vertex;
                if( visited[ nextVertex ]  )
                    continue;
                this.lowestValueEdge.add( edge );
                float tempDistance = distances[ currentVertex ] + edge.cost;
                if( tempDistance < distances[ nextVertex ] ){
                    distances[ nextVertex ] = tempDistance;
                    previous[ nextVertex ] = currentVertex;
                }
            }
        }
    }

    // O(v) = v 
    private void reconstructPath( int[] previous ){
        int currentVertex = this.targetVertex;
        while( currentVertex != this.initialVertex ){
            this.path.addFirst(currentVertex);
            currentVertex = previous[ currentVertex ];
        }
    }

    // O(v) = v 
    private void calculateTotalTime(){
        this.time = (float)0.0;
        int currentVertex = 0;
        Iterator<Integer> iterator = this.path.iterator();

        while( currentVertex != this.targetVertex && iterator.hasNext() ){
            this.time += this.getWaitingTime( currentVertex, this.time  );
            currentVertex = iterator.next();
        }

        if( currentVertex != this.targetVertex )
            this.time = (float)-1.0;
    }

    public void solve(){
        this.findMinimalPath();
        this.reconstructPath( previous );
        this.calculateTotalTime();
    }

    public void displayAnswer(){
        if( this.time < 0 ){
            System.out.println("Unrecheable.");
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
    public static float waitingTime( int i, float time ){
        return (float)0.5;
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


