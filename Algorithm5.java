//Use of algorithm:
//
//
//Analyses of complexity:
//
//
import java.util.Locale;
import java.util.ArrayList;
import java.io.FileReader; 
import java.util.Scanner;
import java.lang.reflect.Method;
import java.lang.Double;
import java.lang.Integer;
import java.util.function.*;
import java.util.*;


class Edge{
    Edge(){}
}


class EdgeComparator implements Comparator<Edge>{
    public int compare( Edge first, Edge second ){}
}


//There are 2 ways to represent a Graph, eighter as adjacency list or a matrix. Because we do not know wether the Graph is dense,
//it makes sense to use an adjacency list, to decrease time and space complexity for the cases when the graph is sparse, and have equal performance
//when the graph is dense. 
class Graph{

    ArrayList<ArrayList<Integer>> edges;
    PriorityQueue<Edges> edges = new PriorityQueue<Edges>();
    //pq.add();
    //pq.peek(); // acess 
    //pq.poll(); //  acess and delete

    public Graph( int numberOfVertexes ){
        edges = new ArrayList< ArrayList< Integer > >();
        for( int i=0; i<numberOfVertexes; i++ ){
            edges.add( new ArrayList< Integer >() );
        } 
    }

    public void addEdge( int vertex, int otherVertex, double value ){
        edges.get(vertex).add( otherVertex );
    }

    //Implementation of Djakstra`s algorithm.
    //How to pass a function as a parameter and receive it here ??
    public float calculateMinimalPath( int initialVertex, int targetVertex, BiFunction< Integer, Double, Double > wait ){
    //public static float calculateMinimalPath( int initialVertex, int targetVertex, Method wait ){
    
        double waitingTime = wait.apply( 0, 0.53 );
        //double waitingTime = wait.invoke( 0, 0.53 );

        return 5;
    }

}


//Placeholder for the waiting function that will ge given.
class ExampleWaitingFunction{

    double waitingTime( int i, double time ){
        return 0.5;
    }

}



class Algorithm5{

    public static void main( String args[] ) throws Exception {
        
        Locale.setDefault(Locale.US);
        int numberOfVertexes;
        int number_of_edges;
        FileReader reader = new FileReader( args[0] );
        Scanner scan = new Scanner( reader );
        numberOfVertexes = scan.nextInt();
        scan.nextLine();
        number_of_edges = scan.nextInt();
        scan.nextLine();
        int firstVertex, secondVertex;
        Graph graph = new Graph( numberOfVertexes );

        //Method method;
        //Object[] parameters = {Integer.class, Double.class};
        //method = ExampleWaitingFunction.class.getMethod("waitingTime", parameters );

        for( int i=0; i<number_of_edges; i++ ){
            firstVertex = scan.nextInt();
            secondVertex = scan.nextInt();
            double weight = scan.nextFloat();
            scan.nextLine();
            //change on the third parameter to pass the real waiting function.
            graph.addEdge( firstVertex, secondVertex, weight );
        }
    
        //System.out.println(graph.calculateMinimalPath(0, numberOfVertexes-1, method));
        //graph.calculateMinimalPath( 0, numberOfVertexes-1, method );
        //graph.calculateMinimalPath( 0, numberOfVertexes-1, ExampleWaitingFunction::waitingTime );

    }
}



